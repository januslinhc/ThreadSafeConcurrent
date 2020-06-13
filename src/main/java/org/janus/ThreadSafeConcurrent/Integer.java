package org.janus.ThreadSafeConcurrent;

public class Integer {
    private volatile int value = 0;

    public Integer() {
    }

    public int get() {
        return this.value;
    }

    private void set(int value) {
        this.value = value;
    }

    private boolean equal(int value) {
        return get() == value;
    }

    private boolean equalAndSet(int targetValue, int newValue) {
        if (equal(targetValue)) {
            set(newValue);
            return true;
        }
        return false;
    }

    public int getAndIncrease() {
        int originalValue = get();
        // attempt to increase until successful
        while (!equalAndSet(originalValue, originalValue + 1)) {
            originalValue = get();
        }
        return originalValue;
    }
}
