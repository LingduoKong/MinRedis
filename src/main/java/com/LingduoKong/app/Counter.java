package com.LingduoKong.app;

import java.util.HashMap;

/**
 * Created by lingduokong on 2/26/16.
 */
public class Counter {
    private HashMap<String, Integer> valueCounterTable;

    public Counter() {
        valueCounterTable = new HashMap<>();
    }

    public void addCounterByValue(String value) {
        if (value == null) {
            return;
        }
        Integer count = valueCounterTable.get(value);
        if (count == null) {
            count = 1;
        } else {
            count += 1;
        }
        valueCounterTable.put(value, count);
    }

    public void minusCounterByValue(String value) {
        if (value == null) {
            return;
        }
        Integer count = valueCounterTable.get(value);
        if (count == null || count <= 0) {
            return;
        }
        count -= 1;
        valueCounterTable.put(value, count);
    }

    public Integer get(String key) {
        return valueCounterTable.get(key);
    }

}
