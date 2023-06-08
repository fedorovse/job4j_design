package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @XmlAttribute
    private int id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String sureName;

    @XmlAttribute
    private boolean sex;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSureName() {
        return sureName;
    }

    public boolean isSex() {
        return sex;
    }

    public Car getCar() {
        return car;
    }

    public String[] getSkills() {
        return skills;
    }

    private Car car;

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private String[] skills;

    public Employee() {
    }

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
