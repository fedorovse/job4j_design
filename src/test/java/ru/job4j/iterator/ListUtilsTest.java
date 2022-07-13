package ru.job4j.iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 3, 3);
    }

    @Test
    public void whenRemoveIfLessThanZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, -1, 2, -5));
        ListUtils.removeIf(input, x -> x < 0);
        assertThat(input, is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenRemoveIfThanEmptyList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-1, -5));
        ListUtils.removeIf(input, x -> x < 0);
        assertThat(input, is(Arrays.asList()));
    }

    @Test
    public void whenReplaceIfLessThanZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, -1, 2, -5));
        ListUtils.replaceIf(input, x -> x < 0, 0);
        assertThat(input, is(Arrays.asList(1, 0, 2, 0)));
    }

    @Test
    public void whenReplaceIfEmptyList() {
        List<Integer> input = new ArrayList<>();
        ListUtils.replaceIf(input, x -> x < 0, 0);
        assertThat(input, is(Arrays.asList()));
    }

    @Test
    public void whenRemoveList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        List<Integer> removeList = new ArrayList<>(Arrays.asList(2, 0));
        ListUtils.removeAll(input, removeList);
        assertThat(input, is(Arrays.asList(1, 3)));
    }
}