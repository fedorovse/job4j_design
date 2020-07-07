package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test(expected = NoSuchElementException.class)
    public void whenAddOneValueThanItOne() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator<Integer> it = set.iterator();
        Integer result = it.next();
        assertThat(result, is(1));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> array = new SimpleSet<>();
        array.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddTwoEqualValuesThanItOne() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        it.next();
    }

    @Test
    public void whenAddNullValue() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(null);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(nullValue()));
        assertThat(it.next(), is(2));
    }
}
