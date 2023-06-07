package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
public class Car {

    @XmlAttribute
    private String color;

    @XmlAttribute
    private String brand;

    @XmlAttribute
    private String number;

    public Car() {
    }

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
