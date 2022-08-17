package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader configReader = new BufferedReader(new FileReader(this.path))) {
            String confStr;
            int index;
            while ((confStr = configReader.readLine()) != null) {
                if (confStr.startsWith("#") || confStr.equals("")) {
                    continue;
                }
                index = confStr.indexOf("=");
                if (index == -1
                        || confStr.substring(0, index).equals("")
                        || confStr.substring(index + 1).equals("")) {
                    throw new IllegalArgumentException();
                }
                values.put(confStr.substring(0, index), confStr.substring(index + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (this.values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
