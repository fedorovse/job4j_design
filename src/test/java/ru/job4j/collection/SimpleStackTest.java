package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleStackTest {

    SimpleStack<Integer> stack;

    @Before
    public void initData() {
        stack = new SimpleStack<>();
    }

    @Test
    public void whenPushAndPop() {
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPopThenPushPop() {
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPopPop() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }
}