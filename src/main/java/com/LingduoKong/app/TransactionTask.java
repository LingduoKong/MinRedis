package com.LingduoKong.app;

import java.util.HashMap;

/**
 * Created by lingduokong on 2/26/16.
 */
public class TransactionTask {

    HashMap<String, String> table;

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
        table.remove(key);
    }


}
