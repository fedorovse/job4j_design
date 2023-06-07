package ru.job4j.io.serialization.json;

public class Car {
    private final String color;
    private final String brand;
    private final String number;

    public Car(String color, String brand, String number) {
        this.color = color;
        this.brand = brand;
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Car{"
                + "color='" + color + '\''
                + ", brand='" + brand + '\''
                + ", number='" + number + '\''
                + '}';
    }
}
