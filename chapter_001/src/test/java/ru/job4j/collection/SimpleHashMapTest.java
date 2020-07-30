package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {

    @Test
    public void whenInsertOneThenGetOne() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert("1", "a");
        String expected = "a";
        assertThat(strings.get("1"), is(expected));
    }

    @Test
    public void whenInsertTwoEqualThenGetOne() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert("1", "a");
        strings.insert("1", "b");
        String expected = "b";
        assertThat(strings.get("1"), is(expected));
    }

    @Test
    public void whenInsertOneAndDeleteThenGetNull() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert("1", "a");
        strings.delete("1");
        assertThat(strings.get("1"), is(nullValue()));
    }
}
