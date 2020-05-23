package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {
    private Iterator simplyArrayIter;

    @Test(expected = NoSuchElementException.class)
    public void whenFourIntElements() {
        SimpleArray<Integer> array= new SimpleArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        simplyArrayIter = array.iterator();
        assertThat(simplyArrayIter.hasNext(), is(true));
        assertThat(simplyArrayIter.next(), is(1));
        assertThat(simplyArrayIter.hasNext(), is(true));
        assertThat(simplyArrayIter.next(), is(2));
        assertThat(simplyArrayIter.hasNext(), is(true));
        assertThat(simplyArrayIter.next(), is(3));
        assertThat(simplyArrayIter.hasNext(), is(true));
        assertThat(simplyArrayIter.next(), is(4));
        assertThat(simplyArrayIter.hasNext(), is(false));
        simplyArrayIter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyArray() {
        SimpleArray<Integer> array = new SimpleArray<>();
        simplyArrayIter = array.iterator();
        assertThat(simplyArrayIter.hasNext(), is(false));
        simplyArrayIter.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddThreeAndGetFourElements() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(3));
        array.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElements() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.set(0, 5);
        assertThat(array.get(0), is(5));
        array.set(3, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveElements() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);
        array.add(2);
        array.remove(0);
        assertThat(array.get(0), is(2));
        array.remove(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArraySizeThreeElements() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(3));
        array.add(4);
    }
}
