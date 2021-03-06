package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushFirstThenPollFirst() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushFirst(1);
        assertThat(stack.popFirst(), is(1));
    }

    @Test
    public void whenPushFirstPollFirstThenPushFirstPollFirst() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushFirst(1);
        stack.popFirst();
        stack.pushFirst(2);
        assertThat(stack.popFirst(), is(2));
    }

    @Test
    public void whenPushFirstPushFirstThenPollFirstPollFirst() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushFirst(1);
        stack.pushFirst(2);
        stack.popFirst();
        assertThat(stack.popFirst(), is(1));
    }

    @Test
    public void whenSizeTwo() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        int result = stack.size();
        assertThat(result, is(2));
    }

    @Test
    public void whenPopOneValueThanSizeTwo() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        int result = stack.size();
        assertThat(result, is(2));
    }
}
