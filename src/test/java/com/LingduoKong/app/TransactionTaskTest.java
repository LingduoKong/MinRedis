package com.LingduoKong.app;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by lingduokong on 2/26/16.
 */
public class TransactionTaskTest {

    static TransactionTask transactionTask;

    @BeforeClass
    public static void before() throws Exception {
        transactionTask = new TransactionTask();
    }

    @Test
    public void testSetAndGet() throws Exception {
        transactionTask.set("a", String.valueOf(10));
        Assert.assertEquals("10", transactionTask.get("a"));
    }

    @Test
    public void testUnset() throws Exception {
        transactionTask.set("a", String.valueOf(20));
        Assert.assertEquals("20", transactionTask.get("a"));
        transactionTask.unSet("a");
        Assert.assertEquals(null, transactionTask.get("a"));
    }

    @Test
    public void testRemove() throws Exception {
        transactionTask.set("a", "100");
        transactionTask.remove("a");
        Assert.assertEquals(false, transactionTask.getTable().containsKey("a"));
    }

    @Test
    public void testHasKey() throws Exception {
        transactionTask.set("a", "100");
        transactionTask.unSet("a");
        Assert.assertEquals(true, transactionTask.hasKey("a"));
        transactionTask.remove("a");
        Assert.assertEquals(false, transactionTask.hasKey("a"));
    }


}
