package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsSergey() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Sergey"));
    }

    @Test
    public void whenAddDuplicateAndFindThenRolenameIsSergey() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        store.add(new Role("1", "Vania", 63.5));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Sergey"));
    }

    @Test
    public void whenAddAndFindThenNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        Role result = store.findById("5");
        assertNull(result);
    }

    @Test
    public void whenAddAndReplaceThenRolenameIsVova() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        store.replace("1", new Role("1", "Vova", 78.2));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Vova"));
    }

    @Test
    public void whenAddAndReplaceThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        boolean result = store.replace("2", new Role("2", "Vova", 78.2));
        assertFalse(result);
    }

    @Test
    public void whenAddAndDeleteThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        boolean result = store.delete("1");
        assertTrue(result);
    }

    @Test
    public void whenAddAndDeleteThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Sergey", 85.5));
        boolean result = store.delete("2");
        assertFalse(result);
    }
}