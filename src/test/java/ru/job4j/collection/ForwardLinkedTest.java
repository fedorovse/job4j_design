package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

public class ForwardLinkedTest {

    ForwardLinked<Integer> linked;

    @Before
    public void setUp() {
        linked = new ForwardLinked<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenMultiAddFirstAndDelete() {
        linked.addFirst(1);
        linked.addFirst(2);
        assertThat(linked.deleteFirst(), is(2));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAddThenIter() {
        linked.add(1);
        linked.add(2);
        Iterator<Integer> iter = linked.iterator();
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> iter = linked.iterator();
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(1));
    }

    @Test
    public void whenSize0RevertReturnFalse() {
        assertFalse(linked.revert());
    }

    @Test
    public void whenSize1RevertReturnFalse() {
        linked.add(1);
        assertFalse(linked.revert());
    }

}