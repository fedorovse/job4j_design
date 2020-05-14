package ru.job4j.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

public class ArrayItTest {
    private ArrayIt iter;

    @Before
    public void before() {
        iter = new ArrayIt(new int[] {1, 2, 3});
    }

    @Test
    public void whenMultiCallhasNextThenTrue() {
        Assert.assertThat(iter.hasNext(), is(true));
        Assert.assertThat(iter.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        Assert.assertThat(iter.next(), is(1));
        Assert.assertThat(iter.next(), is(2));
        Assert.assertThat(iter.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        iter = new ArrayIt(new int[] {});
        iter.next();
    }
}
