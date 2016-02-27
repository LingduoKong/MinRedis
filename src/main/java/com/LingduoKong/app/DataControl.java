package com.LingduoKong.app;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by lingduokong on 2/26/16.
 */
public class DataControl {

    private LinkedList<TransactionTask> transactionStack;
    private TransactionTask permanent;
    private Counter counter;

    public DataControl() {
        transactionStack = new LinkedList<>();
        permanent = new TransactionTask();
        counter = new Counter();
    }

    public void beginNewTransaction() {
        transactionStack.addLast(new TransactionTask());
    }

    public void rollBack() {
        if (!transactionStack.isEmpty()) {
            TransactionTask curTask = transactionStack.removeLast();
            for (Map.Entry<String, String> entry : curTask.getTable().entrySet()) {
                if (entry.getValue() == null) {
                    addCounterByKey(entry.getKey());
                } else {
                    minusCounterByKey(entry.getKey());
                }
            }
        } else {
            System.out.println("NO TRANSACTION");
        }
    }

    public void commit() {
        while (transactionStack.size() > 0) {
            TransactionTask curTask = transactionStack.removeFirst();

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

        for (int i = transactionStack.size() - 1; i >= 0; i--) {
            TransactionTask task = transactionStack.get(i);
            if (task.hasKey(key)) {
                return task.get(key);
            }
        }
        return permanent.get(key);
    }

    public void set(String key, String value) {
        minusCounterByKey(key);
        if (transactionStack.size() > 0) {
            transactionStack.getLast().set(key, value);
        } else {
            permanent.set(key, value);
        }
        addCounterByKey(key);
    }

    public int numberEqualsTo(String value) {
        Integer num = counter.get(value);
        return num == null ? 0 : num;
    }

    public void unset(String key) {
        minusCounterByKey(key);
        if (transactionStack.size() > 0) {
            transactionStack.getLast().unSet(key);
        } else {
            permanent.remove(key);
        }
    }

    private void addCounterByKey(String key) {
        String value = get(key);
        counter.addCounterByValue(value);
    }

    private void minusCounterByKey(String key) {
        String value = get(key);
        counter.minusCounterByValue(value);
    }
}
