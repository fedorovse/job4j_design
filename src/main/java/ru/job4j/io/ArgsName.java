package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Валидация и парсинг входных аргументов
 */
public class ArgsName {

    /**
     * values - хранит в Мар тип аргумента и его значение
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Проверка параметра и поиск его значения
     * @param key String - название параметра
     * @return String - значение соответствующее переданному параметру
     * если параметр не валиден, то кинет IllegalArgumentException
     */
    public String get(String key) {
        if (!keyValidation(key)) {
            throw new IllegalArgumentException("key: " + key + " not found");
        }
        return values.get(key);
    }

    /**
     * Парсит переданные аргументы в Мар(тип параметра, его значение)
     * @param args массив String - входящие аргументы
     */
    private void parse(String[] args) {
        if (!validation(args)) {
            throw new IllegalArgumentException("input arguments are not valid");
        }
        for (String arg : args) {
            String key = arg.substring(1, arg.indexOf("="));
            String value = arg.substring(arg.indexOf("=") + 1);
            values.put(key, value);
        }
    }

    private boolean keyValidation(String key) {
        return values.containsKey(key);
    }

    private boolean validation(String[] args) {
        if (args.length == 0) {
            return false;
        }
        for (String arg : args) {
            if (!arg.startsWith("-")
                    || !arg.contains("=")
                    || arg.indexOf("=") < 2
                    || arg.substring(arg.indexOf("=")).length() < 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Создание объекта ArgsName и парсинг переданных аргументов
     * @param args массив String с переданными аргументами
     * @return объект ArgsName
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
        System.out.println(zip.get("encoding"));
    }
}
