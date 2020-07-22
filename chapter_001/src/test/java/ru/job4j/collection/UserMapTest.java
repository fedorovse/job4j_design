package ru.job4j.collection;


import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserMapTest {

    @Test
    public void whenPutTwoEqualUsersThenPrintOne() {
        User user1 = new User("Sergey", 2, new GregorianCalendar(2020, 07, 17));
        User user2 = new User("Sergey", 2, new GregorianCalendar(2020, 07, 17));
        Map<User, Object> users = new HashMap<>();
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users);
    }
}
