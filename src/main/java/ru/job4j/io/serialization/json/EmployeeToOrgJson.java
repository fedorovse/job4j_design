package ru.job4j.io.serialization.json;

import org.json.JSONObject;

public class EmployeeToOrgJson {

    public static void main(String[] args) {
        Employee jamesBond = new Employee(123, "Bond", "James", true,
                new Car("silver", "Aston Martin DB5", "JB007"),
                new String[] {"crash", "kill", "destroy"});
        JSONObject jamesBondJson = new JSONObject(jamesBond);
        System.out.println(jamesBondJson);

        String stringJamesBond = "{\"id\":123,\"name\":\"Bond\",\"surname\":\"James\",\"sex\":true,"
                + "car:{\"color\":\"silver\",\"brand\":\"Aston Martin DB5\",\"number\":\"JB007\"},"
                + "\"skills\":[\"crash\",\"kill\",\"destroy\"]}";
        JSONObject jamesBond2 = new JSONObject(stringJamesBond);
        System.out.println(jamesBond2);

    }
}
