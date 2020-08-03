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
    public void whenInsertTwoEqualThenGetLastOne() {
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

    @Test
    public void whenInsertNullValueGetNull() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert("1", null);
        assertThat(strings.get("1"), is(nullValue()));
    }

    @Test
    public void whenInsertNullKeyGetValue() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert(null, "c");
        assertThat(strings.get(null), is("c"));
    }

    @Test
    public void whenInsertTwoNullKeysGetLastValue() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert(null, "c");
        strings.insert(null, "d");
        assertThat(strings.get(null), is("d"));
    }

    @Test
    public void whenInsert26ThenResize() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        for (int index = 0; index < 13; index++) {
            strings.insert("123" + index, "c" + index);
        }
        assertThat(strings.getSize(), is(12));
        for (int index = 0; index < 13; index++) {
            strings.insert("index" + index, "ccc" + index);
        }
        assertThat(strings.getSize(), is(25));
    }

    @Test
    public void whenInsertInOneBasketThenFalse() {
        SimpleHashMap<String, String> strings = new SimpleHashMap<>();
        strings.insert("11", "c1");
        assertThat(strings.get("11"), is("c1"));
        assertThat(strings.insert("110", "c10"), is(false));
        assertThat(strings.get("11"), is("c1"));
        assertThat(strings.get("110"), is(nullValue()));
    }
}
