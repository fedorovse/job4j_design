package ru.job4j.set;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {

    private Set<Integer> set;

    @Before
    public void init() {
        set = new SimpleSet<>();
    }

    @Test
    public void whenAddNonNull() {
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorFromEmptySet() {
        set.iterator().next();
    }

    @Test
    public void whenIteratorFromNotEmptySet() {
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), Is.is(2));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenAddDuplicate() {
        set.add(1);
        set.add(2);
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.next(), Is.is(1));
        assertThat(iterator.next(), Is.is(2));
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModificationIterator() {
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        set.add(3);
        iterator.next();
    }
}