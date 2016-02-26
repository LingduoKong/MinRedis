package com.LingduoKong.app;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by lingduokong on 2/26/16.
 */
public class DataControl {

    Deque<TransactionTask> transactionQueue;

    TransactionTask perminenet;

    public DataControl() {
        transactionQueue = new LinkedList<>();
        perminenet = new TransactionTask();
    }

    public void beginNewTransaction() {

    }

    public void rollBack() {

    }

    public void commit() {

    }

    public String get(String key) {


        return null;
    }

    public void set(String key, String value) {

    }

    public int numberEqualsTo(String num) {

        return 0;
    }

    public void unset(String key) {

    }

}
