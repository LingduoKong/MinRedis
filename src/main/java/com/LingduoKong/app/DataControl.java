package com.LingduoKong.app;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by lingduokong on 2/26/16.
 */
public class DataControl {

    private LinkedList<TransactionTask> transactionQueue;
    private TransactionTask permanent;

    public DataControl() {
        transactionQueue = new LinkedList<>();
        permanent = new TransactionTask();
    }

    public void beginNewTransaction() {
        transactionQueue.addLast(new TransactionTask());
    }

    public void rollBack() {
        if (transactionQueue.size() > 0) {
            transactionQueue.removeLast();
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
            if (task.get(key) != null) {
                return task.get(key);
            }
        }

        return permanent.get(key);
    }

    public void set(String key, String value) {
        if (transactionQueue.size() > 0) {
            transactionQueue.getLast().set(key, value);
        } else {
            permanent.set(key, value);
        }
    }

    public int numberEqualsTo(String num) {

        return 0;
    }

    public void unset(String key) {
        if (transactionQueue.size() > 0) {
            transactionQueue.getLast().unSet(key);
        } else {
            permanent.remove(key);
        }
    }

}
