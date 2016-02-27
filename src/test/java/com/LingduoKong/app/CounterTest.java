package com.LingduoKong.app;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by lingduokong on 2/26/16.
 */
public class CounterTest {

    static Counter counter;

    @BeforeClass
    public static void beforeClass() {
        counter = new Counter();
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(null, counter.get(null));
        Assert.assertEquals(null, counter.get("c"));
        counter.addCounterByValue("c");
        Assert.assertEquals(new Integer(1), counter.get("c"));
    }

    @Test
    public void testMinus() throws Exception {
        counter.addCounterByValue("a");
        Assert.assertEquals(new Integer(1), counter.get("a"));
        counter.minusCounterByValue("a");
        Assert.assertEquals(new Integer(0), counter.get("a"));
        counter.minusCounterByValue("a");
        Assert.assertEquals(new Integer(0), counter.get("a"));
    }

    @Test
    public void testAdd() throws Exception {
        counter.addCounterByValue("b");
        Assert.assertEquals(new Integer(1), counter.get("b"));
        counter.addCounterByValue("b");
        Assert.assertEquals(new Integer(2), counter.get("b"));
        counter.addCounterByValue(null);
        Assert.assertEquals(null, counter.get(null));
    }
}
