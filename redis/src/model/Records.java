package model;

import java.util.Stack;

public class Records<KEY,VALUE> {
    private final KEY key;
    private final VALUE value;
    private final long insertionTime;
    private final long expiryTime;

    public Records(KEY key, VALUE value, long insertionTime, long expiryTime) {
        this.key = key;
        this.value = value;
        this.insertionTime = insertionTime;
        this.expiryTime = expiryTime;
    }

    public KEY getKey() {
        return key;
    }

    public VALUE getValue() {
        return value;
    }

    public long getInsertionTime() {
        return insertionTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
