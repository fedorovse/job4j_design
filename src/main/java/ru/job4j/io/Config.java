package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * В классе реализованы методы для работы с файлом конфигурации
 */
public class Config {

    /**
     * path - путь к файлу конфигурации
     */
    private final String path;

    /**
     * values - содержит ключи и их значения считанные из файла конфигурации. Изначально пустой.
     */
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Чтение файла конфигурации. Пустые строки и комментарии игнорируются.
     * Заполнение values ключами и значениями из файла.
     * @throws IllegalArgumentException при отсутствии разделителя "=" и/или ключа, значения.
     */
    public void load() {
        try (BufferedReader configReader = new BufferedReader(new FileReader(this.path))) {
            String confStr;
            int index;
            while ((confStr = configReader.readLine()) != null) {
                if (confStr.startsWith("#") || confStr.isBlank()) {
                    continue;
                }
                index = confStr.indexOf("=");
                if (index == -1) {
                    throw new IllegalArgumentException(
                            String.format("String does not contain a separator \"=\" {%s}", confStr)
                    );
                }
                String key = confStr.substring(0, index);
                String value = confStr.substring(index + 1);
                if (key.isBlank() || value.isBlank()) {
                    throw new IllegalArgumentException(
                            String.format("No key and/or value in line: {%s}", confStr)
                    );
                }
                values.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает значение, если оно было ранее загружено из файла конфигурации
     * @throws UnsupportedOperationException - если не было загрузки из файла конфигурации
     * @param key String - ключ
     * @return String - загруженное значение соответствующее переданному ключу
     */
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
