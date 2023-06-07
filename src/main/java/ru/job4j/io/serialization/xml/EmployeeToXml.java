package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.json.Car;
import ru.job4j.io.serialization.json.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class EmployeeToXml {
    public static void main(String[] args) throws JAXBException, IOException {

        Employee employee = new Employee(123, "Bond", "James", true,
                new Car("silver", "Aston Martin DB5", "JB007"),
                new String[] {"crash", "kill", "destroy"});

        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        try (StringReader reader = new StringReader(xml)) {
            Employee  employeeFromXml = (Employee) unmarshaller.unmarshal(reader);
            System.out.println(employeeFromXml);
        }

    }
}
