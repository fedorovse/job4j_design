package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleLinkedListTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        String result = array.get(0);
        assertThat(result, is("first"));
    }

    @Test
    public void whenAddFiveElements() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("one");
        array.add("two");
        array.add("three");
        array.add("four");
        array.add("five");
        String result = array.get(4);
        assertThat(result, is("five"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        boolean rsl = it.hasNext();
        String result = it.next();
        assertThat(rsl, is(true));
        assertThat(result, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBounds() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenEmptyIt() {
        SimpleLinkedList<String> array = new SimpleLinkedList<>();
        array.iterator().next();
    }

    
}
