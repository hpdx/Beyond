package com.dating.rxbus.finder;


import com.dating.rxbus.entity.EventType;
import com.dating.rxbus.entity.SubscriberEvent;

import java.util.Map;
import java.util.Set;

/**
 * Finds producer and subscriber methods.
 */
public interface Finder {

    Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener);

    Finder ANNOTATED = new Finder() {
        @Override
        public Map<EventType, Set<SubscriberEvent>> findAllSubscribers(Object listener) {
            return AnnotatedFinder.findAllSubscribers(listener);
        }
    };

}
