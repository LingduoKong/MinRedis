package com.LingduoKong.app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by lingduokong on 2/26/16.
 */
public class DataControl {

    private LinkedList<TransactionTask> transactionQueue;
    private TransactionTask permanent;
    private HashMap<String, Integer> valueCounterTable;

    public DataControl() {
        transactionQueue = new LinkedList<>();
        permanent = new TransactionTask();
        valueCounterTable = new HashMap<>();
    }

    public void beginNewTransaction() {
        transactionQueue.addLast(new TransactionTask());
    }

    public void rollBack() {
        if (transactionQueue.size() > 0) {
            TransactionTask curTask = transactionQueue.removeLast();
            for (Map.Entry<String, String> entry : curTask.getTable().entrySet()) {
                if (entry.getValue() == null) {
                    addCounterByKey(entry.getKey());
                } else {
                    minusCounterByValue(entry.getValue());
                }
            }
        } else {
            System.out.println("NO TRANSACTION");
        }
    }

    public void commit() {
        while (transactionQueue.size() > 0) {
            TransactionTask curTask = transactionQueue.removeFirst();

            for (Map.Entry<String, String> entry : curTask.getTable().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    permanent.remove(key);
                } else {
                    permanent.set(key, value);
                }
            }
        }
    }

    public String get(String key) {

        for (int i = transactionQueue.size() - 1; i >= 0; i--) {
            TransactionTask task = transactionQueue.get(i);
            if (task.hasKey(key)) {
                return task.get(key);
            }
        }
        return permanent.get(key);
    }

    public void set(String key, String value) {
        minusCounterByKey(key);
        if (transactionQueue.size() > 0) {
            transactionQueue.getLast().set(key, value);
        } else {
            permanent.set(key, value);
        }
        addCounterByKey(key);
    }

    public int numberEqualsTo(String value) {
        Integer num = valueCounterTable.get(value);
        return num == null ? 0 : num;
    }

    public void unset(String key) {
        minusCounterByKey(key);
        if (transactionQueue.size() > 0) {
            transactionQueue.getLast().unSet(key);
        } else {
            permanent.remove(key);
        }
    }

    private void addCounterByKey(String key) {
        String value = get(key);
        addCounterByValue(value);
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

    private void minusCounterByKey(String key) {
        String value = get(key);
        minusCounterByValue(value);
    }

    private void minusCounterByValue(String value) {
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

}
