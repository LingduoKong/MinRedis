package com.LingduoKong.app;

import java.util.HashMap;

/**
 * Created by lingduokong on 2/26/16.
 */
public class TransactionTask {

    private HashMap<String, String> table;

    public TransactionTask() {
        table = new HashMap<>();
    }

    public void set(String key, String value) {
        table.put(key, value);
    }

    public String get(String key) {
        return table.get(key);
    }

    public void unSet(String key) {
        table.put(key, null);
    }

    public void remove(String key) {
        table.remove(key);
    }

    public HashMap<String, String> getTable() {
        return table;
    }

    public boolean hasKey(String key) {
        return table.containsKey(key);
    }

}
