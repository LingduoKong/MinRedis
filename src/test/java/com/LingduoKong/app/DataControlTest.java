package com.LingduoKong.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lingduokong on 2/26/16.
 */
public class DataControlTest {
    
    DataControl dataControl;

    @Before
    public void setUp() throws Exception {
        dataControl = new DataControl();
    }

    @Test
    public void testGetAndSet() throws Exception {
        dataControl.set("ex", "10");
        Assert.assertEquals("10", dataControl.get("ex"));
        dataControl.unset("ex");
        Assert.assertEquals(null, dataControl.get("ex"));
    }

    @Test
    public void testNumberEqualsTo() throws Exception {
        dataControl.set("a", "10");
        dataControl.set("b", "10");
        Assert.assertEquals(2, dataControl.numberEqualsTo("10"));
        Assert.assertEquals(0, dataControl.numberEqualsTo("20"));
        dataControl.set("b", "30");
        Assert.assertEquals(1, dataControl.numberEqualsTo("10"));
    }

    @Test
    public void testTransaction() throws Exception {
        Assert.assertEquals(null, dataControl.get("a"));

        dataControl.beginNewTransaction();
        dataControl.set("a", "10");
        Assert.assertEquals("10", dataControl.get("a"));
        dataControl.beginNewTransaction();
        dataControl.set("a", "20");
        Assert.assertEquals("20", dataControl.get("a"));
        dataControl.rollBack();
        Assert.assertEquals("10", dataControl.get("a"));
        dataControl.rollBack();
        Assert.assertEquals(null, dataControl.get("a"));
    }

    @Test
    public void testTransaction2() throws Exception {
        dataControl.set("a", "50");
        dataControl.beginNewTransaction();
        Assert.assertEquals("50", dataControl.get("a"));
        dataControl.set("a", "60");
        dataControl.beginNewTransaction();
        dataControl.unset("a");
        Assert.assertEquals(null, dataControl.get("a"));
        dataControl.rollBack();
        Assert.assertEquals("60", dataControl.get("a"));
        dataControl.commit();
        Assert.assertEquals("60", dataControl.get("a"));
    }

    @Test
    public void testTransaction3() throws Exception {
        dataControl.set("a", "10");
        dataControl.beginNewTransaction();
        Assert.assertEquals(1, dataControl.numberEqualsTo("10"));
        dataControl.beginNewTransaction();
        dataControl.unset("a");
        Assert.assertEquals(0, dataControl.numberEqualsTo("10"));
        dataControl.rollBack();
        Assert.assertEquals(1, dataControl.numberEqualsTo("10"));
        dataControl.commit();
    }
}

