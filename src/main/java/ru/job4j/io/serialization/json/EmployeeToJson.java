package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeToJson {

    public static void main(String[] args) {
        Employee employee = new Employee(123, "Bond", "James", true,
                new Car("silver", "Aston Martin DB5", "JB007"),
                new String[] {"crash", "kill", "destroy"});

        Gson gson = new GsonBuilder().create();
        String stringGson = gson.toJson(employee);
        System.out.println(stringGson);

        String employeeJson =
                "{"
                        + "\"id\":123,"
                        + "\"name\":\"Bond\","
                        + "\"sureName\":\"James\","
                        + "\"sex\":true,"
                        + "car:{\"color\":\"silver\",\"brand\":\"Aston Martin DB5\",\"number\":\"JB007\"},"
                        + "\"skills\":[\"crash\", \"kill\",\"destroy\"]"
                + "}";
        Employee employeeFromJson = gson.fromJson(employeeJson, Employee.class);
        System.out.println(employeeFromJson);
    }
}
