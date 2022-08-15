package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * В классе реализована простая обработка log файлов.
 */
public class LogFilter {

    /**
     * Осуществляет в логе поиск строк содержащих "404" на предпоследней позиции
     * @param file String - путь к лог файлу.
     * @return List с найденными строками
     */
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String logString;
            while ((logString = reader.readLine()) != null) {
                String[] parts = logString.split(" ");
                if ("404".equals(parts[parts.length - 2])) {
                    rsl.add(logString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Записывает переданный List в файл
     * @param log List<String> - лист, который будет записан в файл
     * @param file String - путь к файлу
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter saver = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            log.forEach(saver::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
        log.forEach(System.out::println);
    }
}
