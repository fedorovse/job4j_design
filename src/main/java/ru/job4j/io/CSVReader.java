package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Парсит CSV файл по заданному разделителю и возвращает нужные столбцы.
 * Пример: -path=file.csv -delimiter=;  -out=stdout -filter=name,age
 * Путь к файлу источнику, разделитель, куда выводить (stdout - в консоль,
 * либо задать путь к файлу), далее фильтр - через запятую, какие столбцы нужно вернуть.
 */
public class CSVReader {

    /**
     * path - путь к файлу источнику (который парсим)
     */
    private String path;
    /**
     * delimiter - разделитель, который используется в данном файле
     */
    private String delimiter;
    /**
     * out - куда сделать вывод. stdout - в консоль, либо путь к файлу
     */
    private String out;
    /**
     * filter - лист с названиями столбцов, которые будем выводить
     */
    private final List<String> filter = new ArrayList<>();
    /**
     * fileStrings - исходный файл разбитый на строки
     */
    List<List<String>> fileStrings = new ArrayList<>();

    private void validate(ArgsName argsName) {
        path = argsName.get("path");
        if (!Files.isRegularFile(Path.of(path))) {
            throw new IllegalArgumentException("file not found");
        }
        if (new File(path).length() == 0) {
            throw new IllegalArgumentException("file is empty");
        }
        delimiter = argsName.get("delimiter");
        out = argsName.get("out");
        filter.addAll(Arrays.asList(argsName.get("filter").split(",")));
    }

    private void readCSV() throws IOException {
        List<List<String>> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                Scanner scanner = new Scanner(lineFromFile).useDelimiter(delimiter);
                strings.add(scanner.tokens().collect(Collectors.toList()));
            }
        }
        fileStrings = strings;
    }

    private String filterCSV(List<String> filter) {
        StringBuilder rsl = new StringBuilder();
        String ls = System.lineSeparator();
        List<Integer> index = new ArrayList<>();
        for (String s : filter) {
            if (!fileStrings.get(0).contains(s)) {
                throw new IllegalArgumentException("invalid filter name");
            }
            index.add(fileStrings.get(0).indexOf(s));
        }
        for (List<String> lines : fileStrings) {
            for (int i = 0; i < index.size(); i++) {
                rsl.append(lines.get(index.get(i)));
                if (i < index.size() - 1) {
                    rsl.append(delimiter);
                }
            }
            rsl.append(ls);
        }
        return rsl.toString();
    }

    private void writeData(String data) throws IOException {
        if (out.equals("stdout")) {
            System.out.print(data);
        } else {
            try (BufferedWriter saveResult = new BufferedWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                saveResult.write(data);
            }
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        CSVReader csvReader = new CSVReader();
        csvReader.validate(argsName);
        csvReader.readCSV();
        csvReader.writeData(csvReader.filterCSV(csvReader.filter));
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}