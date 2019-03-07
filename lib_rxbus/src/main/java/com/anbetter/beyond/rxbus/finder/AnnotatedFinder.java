package com.anbetter.beyond.rxbus.finder;

import com.anbetter.beyond.rxbus.annotation.Subscribe;
import com.anbetter.beyond.rxbus.entity.EventType;
import com.anbetter.beyond.rxbus.entity.SubscriberEvent;
import com.anbetter.beyond.rxbus.thread.ThreadMode;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class AnnotatedFinder {

    private static final ConcurrentMap<Class<?>, Map<EventType, Set<SourceMethod>>> SUBSCRIBERS_CACHE =
            new ConcurrentHashMap<>();

    private static void loadAnnotatedSubscriberMethods(Class<?> listenerClass,
                                                       Map<EventType, Set<SourceMethod>> subscriberMethods) {
        loadAnnotatedMethods(listenerClass, subscriberMethods);
    }

    private static void loadAnnotatedMethods(Class<?> listenerClass, Map<EventType, Set<SourceMethod>> subscriberMethods) {
        for (Method method : listenerClass.getDeclaredMethods()) {
            if (method.isBridge()) {
                continue;
            }

            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires "
                            + parameterTypes.length + " arguments.  Methods must require a single argument.");
                }

                Class<?> parameterClazz = parameterTypes[0];
                if (parameterClazz.isInterface()) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + parameterClazz
                            + " which is an interface.  Subscription must be on a concrete class type.");
                }

                if ((method.getModifiers() & Modifier.PUBLIC) == 0) {
                    throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + parameterClazz
                            + " but is not 'public'.");
                }

                Subscribe annotation = method.getAnnotation(Subscribe.class);
                ThreadMode thread = annotation.threadMode();
                int tag = annotation.code();

                EventType type = new EventType(tag, parameterClazz);
                Set<SourceMethod> methods = subscriberMethods.get(type);
                if (methods == null) {
                    methods = new HashSet<>();
                    subscriberMethods.put(type, methods);
                }
                methods.add(new SourceMethod(thread, method));

            }
        }

        SUBSCRIBERS_CACHE.put(listenerClass, subscriberMethods);
    }

    /**
     * This implementation finds all methods marked with a {@link Subscribe} annotation.
     */
    static Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener) {
        Class<?> listenerClass = listener.getClass();
        Map<EventType, Set<SubscriberEvent>> subscribersInMethod = new HashMap<>();

        Map<EventType, Set<SourceMethod>> methods = SUBSCRIBERS_CACHE.get(listenerClass);
        if (null == methods) {
            methods = new HashMap<>();
            loadAnnotatedSubscriberMethods(listenerClass, methods);
        }
        if (!methods.isEmpty()) {
            for (Map.Entry<EventType, Set<SourceMethod>> e : methods.entrySet()) {
                Set<SubscriberEvent> subscribers = new HashSet<>();
                for (SourceMethod m : e.getValue()) {
                    subscribers.add(new SubscriberEvent(listener, m.method, m.thread));
                }
                subscribersInMethod.put(e.getKey(), subscribers);
            }
        }

        return subscribersInMethod;
    }

    private AnnotatedFinder() {
        // No instances.
    }

    private static class SourceMethod {
        private ThreadMode thread;
        private Method method;

        private SourceMethod(ThreadMode thread, Method method) {
            this.thread = thread;
            this.method = method;
        }
    }

}
