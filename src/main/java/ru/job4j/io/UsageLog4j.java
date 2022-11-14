package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 44;
        short weight = 98;
        int size = 1_000_000;
        long money = 1_234_567_890L;
        float pi = 3.14f;
        double d = 123.45678;
        boolean yes = true;
        char a = 'A';
        LOG.debug("age = {}, weight = {}, size = {}, money = {}, pi = {}, d = {}, boolean = {} a = {}",
                age, weight, size, money, pi, d, yes, a);
    }

}