package com.anbetter.beyond.rxbus.entity;

public class EventType {

    private final int tag;

    private final Class<?> clazz;

    private final int hashCode;

    public EventType(int tag, Class<?> clazz) {
        if (clazz == null) {
            throw new NullPointerException("EventType Clazz cannot be null.");
        }

        this.tag = tag;
        this.clazz = clazz;

        // Compute hash code eagerly since we know it will be used frequently and we cannot estimate the runtime of the
        // target's hashCode call.
        final int prime = 31;
        hashCode = (prime + tag) * prime + clazz.hashCode();
    }

    @Override
    public String toString() {
        return "[EventType " + tag + " && " + clazz + "]";
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final EventType other = (EventType) obj;

        return tag == other.tag && clazz == other.clazz;
    }

}