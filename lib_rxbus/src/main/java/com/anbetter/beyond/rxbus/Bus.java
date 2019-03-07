package com.anbetter.beyond.rxbus;


import com.anbetter.log.MLog;
import com.anbetter.beyond.rxbus.entity.DeadEvent;
import com.anbetter.beyond.rxbus.entity.EventType;
import com.anbetter.beyond.rxbus.entity.SubscriberEvent;
import com.anbetter.beyond.rxbus.finder.Finder;
import com.anbetter.beyond.rxbus.thread.ThreadEnforcer;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;


public class Bus {
    public static final String DEFAULT_IDENTIFIER = "default";

    /**
     * All registered event subscribers, indexed by event type.
     */
    private final ConcurrentMap<EventType, Set<SubscriberEvent>> subscribersByType =
            new ConcurrentHashMap<>();

    /**
     * Identifier used to differentiate the event bus instance.
     */
    private final String identifier;

    /**
     * Thread enforcer for register, unregister, and posting events.
     */
    private final ThreadEnforcer enforcer;

    /**
     * Used to find subscriber methods in register and unregister.
     */
    private final Finder finder;

    private final ConcurrentMap<Class<?>, Set<Class<?>>> flattenHierarchyCache =
            new ConcurrentHashMap<>();

    /**
     * Creates a new Bus named "default" that enforces actions on the main thread.
     */
    public Bus() {
        this(DEFAULT_IDENTIFIER);
    }

    /**
     * Creates a new Bus with the given {@code identifier} that enforces actions on the main thread.
     *
     * @param identifier a brief name for this bus, for debugging purposes.  Should be a valid Java identifier.
     */
    public Bus(String identifier) {
        this(ThreadEnforcer.MAIN, identifier);
    }

    /**
     * Creates a new Bus named "default" with the given {@code enforcer} for actions.
     *
     * @param enforcer Thread enforcer for register, unregister, and post actions.
     */
    public Bus(ThreadEnforcer enforcer) {
        this(enforcer, DEFAULT_IDENTIFIER);
    }

    /**
     * Creates a new Bus with the given {@code enforcer} for actions and the given {@code identifier}.
     *
     * @param enforcer   Thread enforcer for register, unregister, and post actions.
     * @param identifier A brief name for this bus, for debugging purposes.  Should be a valid Java identifier.
     */
    public Bus(ThreadEnforcer enforcer, String identifier) {
        this(enforcer, identifier, Finder.ANNOTATED);
    }

    /**
     * Test constructor which allows replacing the default {@code Finder}.
     *
     * @param enforcer   Thread enforcer for register, unregister, and post actions.
     * @param identifier A brief name for this bus, for debugging purposes.  Should be a valid Java identifier.
     * @param finder     Used to discover event subscribers and producers when registering/unregistering an object.
     */
    Bus(ThreadEnforcer enforcer, String identifier, Finder finder) {
        this.enforcer = enforcer;
        this.identifier = identifier;
        this.finder = finder;
    }

    @Override
    public String toString() {
        return "[Bus \"" + identifier + "\"]";
    }

    /**
     * Registers all subscriber methods on {@code object} to receive events and producer methods to provide events.
     * <p/>
     * If any subscribers are registering for types which already have a producer they will be called immediately
     * with the result of calling that producer.
     * <p/>
     * If any producers are registering for types which already have subscribers, each subscriber will be called with
     * the value from the result of calling the producer.
     *
     * @param object object whose subscriber methods should be registered.
     * @throws NullPointerException if the object is null.
     */
    public void register(Object object) {
        if (object == null) {
            throw new NullPointerException("Object to register must not be null.");
        }
        enforcer.enforce(this);

        Map<EventType, Set<SubscriberEvent>> foundSubscribersMap = finder.findAllSubscribers(object);
        for (EventType type : foundSubscribersMap.keySet()) {
            Set<SubscriberEvent> subscribers = subscribersByType.get(type);
            if (subscribers == null) {
                //concurrent put if absent
                Set<SubscriberEvent> SubscribersCreation = new CopyOnWriteArraySet<>();
                subscribers = subscribersByType.putIfAbsent(type, SubscribersCreation);
                if (subscribers == null) {
                    subscribers = SubscribersCreation;
                }
            }
            final Set<SubscriberEvent> foundSubscribers = foundSubscribersMap.get(type);
            if (!subscribers.addAll(foundSubscribers)) {
                throw new IllegalArgumentException("Object already registered.");
            }
        }
    }

    /**
     * Unregisters all producer and subscriber methods on a registered {@code object}.
     *
     * @param object object whose producer and subscriber methods should be unregistered.
     * @throws IllegalArgumentException if the object was not previously registered.
     * @throws NullPointerException     if the object is null.
     */
    public void unregister(Object object) {
        if (object == null) {
            throw new NullPointerException("Object to unregister must not be null.");
        }
        enforcer.enforce(this);

        Map<EventType, Set<SubscriberEvent>> subscribersInListener = finder.findAllSubscribers(object);
        for (Map.Entry<EventType, Set<SubscriberEvent>> entry : subscribersInListener.entrySet()) {
            Set<SubscriberEvent> currentSubscribers = getSubscribersForEventType(entry.getKey());
            Collection<SubscriberEvent> eventMethodsInListener = entry.getValue();

            if (currentSubscribers != null && currentSubscribers.containsAll(eventMethodsInListener)) {
                for (SubscriberEvent subscriber : currentSubscribers) {
                    if (eventMethodsInListener.contains(subscriber)) {
                        subscriber.invalidate();
                    }
                }
                currentSubscribers.removeAll(eventMethodsInListener);
            } else  {
                MLog.e(object.getClass() + "\t 没注册过或者已被注销" );
            }
        }
    }

    public void post(Object event) {
        post(-1, event);
    }

    public void post(int tag, Object event) {
        if (event == null) {
            throw new NullPointerException("Event to post must not be null.");
        }
        enforcer.enforce(this);

        Set<Class<?>> dispatchClasses = flattenHierarchy(event.getClass());

        boolean dispatched = false;
        for (Class<?> clazz : dispatchClasses) {
            Set<SubscriberEvent> wrappers = getSubscribersForEventType(new EventType(tag, clazz));

            if (wrappers != null && !wrappers.isEmpty()) {
                dispatched = true;
                for (SubscriberEvent wrapper : wrappers) {
                    dispatch(event, wrapper);
                }
            }
        }

        if (!dispatched && !(event instanceof DeadEvent)) {
            post(new DeadEvent(this, event));
        }
    }

    /**
     * Dispatches {@code event} to the subscriber in {@code wrapper}.  This method is an appropriate override point for
     * subclasses that wish to make event delivery asynchronous.
     *
     * @param event   event to dispatch.
     * @param wrapper wrapper that will call the handle.
     */
    protected void dispatch(Object event, SubscriberEvent wrapper) {
        if (wrapper.isValid()) {
            wrapper.handle(event);
        }
    }

    /**
     * Retrieves a mutable set of the currently registered subscribers for {@code type}.  If no subscribers are currently
     * registered for {@code type}, this method may either return {@code null} or an empty set.
     *
     * @param type type of subscribers to retrieve.
     * @return currently registered subscribers, or {@code null}.
     */
    Set<SubscriberEvent> getSubscribersForEventType(EventType type) {
        return subscribersByType.get(type);
    }

    /**
     * Flattens a class's type hierarchy into a set of Class objects.  The set will include all superclasses
     * (transitively), and all interfaces implemented by these superclasses.
     *
     * @param concreteClass class whose type hierarchy will be retrieved.
     * @return {@code concreteClass}'s complete type hierarchy, flattened and uniqued.
     */
    Set<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        Set<Class<?>> classes = flattenHierarchyCache.get(concreteClass);
        if (classes == null) {
            Set<Class<?>> classesCreation = getClassesFor(concreteClass);
            classes = flattenHierarchyCache.putIfAbsent(concreteClass, classesCreation);
            if (classes == null) {
                classes = classesCreation;
            }
        }

        return classes;
    }

    private Set<Class<?>> getClassesFor(Class<?> concreteClass) {
        List<Class<?>> parents = new LinkedList<>();
        Set<Class<?>> classes = new HashSet<>();

        parents.add(concreteClass);

        while (!parents.isEmpty()) {
            Class<?> clazz = parents.remove(0);
            classes.add(clazz);

            Class<?> parent = clazz.getSuperclass();
            if (parent != null) {
                parents.add(parent);
            }
        }
        return classes;
    }
}
