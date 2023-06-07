package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Employee {
    private final int id;
    private final String name;
    private final String sureName;
    private final boolean sex;
    private final Car car;
    private final String[] skills;

    public Employee(int id, String name, String sureName, boolean sex, Car car, String[] skills) {
        this.id = id;
        this.name = name;
        this.sureName = sureName;
        this.sex = sex;
        this.car = car;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", sureName='" + sureName + '\''
                + ", sex=" + sex
                + ", car=" + car
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }
}
