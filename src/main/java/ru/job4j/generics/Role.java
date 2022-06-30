package ru.job4j.generics;

import java.util.Objects;

public class Role extends Base {

    private final String name;
    private final Double salary;

    public Role(String id, String name, Double salary) {
        super(id);
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(name, role.name) && Objects.equals(salary, role.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }
}
