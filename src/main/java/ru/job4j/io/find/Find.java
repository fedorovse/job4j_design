package ru.job4j.io.find;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Find {

    private String dValue;
    private String nValue;
    private String tValue;
    private String oValue;
    Predicate<Path> condition;

    public static void main(String[] args) {
        Find findFiles = new Find();
        ArgsName argsName = ArgsName.of(args);
        findFiles.validation(argsName);
        Path start = Paths.get(findFiles.dValue);
        findFiles.getPredicate();
        try {
            List<Path> fileList = Search.search(start, findFiles.condition);
            findFiles.writeToFile(fileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(List<Path> list) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(oValue)))) {
            for (Path path : list) {
                writer.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPredicate() {
       if ("name".equals(tValue)) {
            condition = p -> nValue.equals(p.getFileName().toString());
        } else if ("mask".equals(tValue)) {
            String s = maskToRegexp(nValue);
            condition = p -> p
                    .getFileName()
                    .toString()
                    .matches(maskToRegexp(s));
        } else {
//            condition = p -> Pattern.compile(nValue).matcher(p.getFileName().toString()).matches();
            System.out.println(nValue);
//            condition = p -> p.getFileName().toString().matches(nValue);
            condition = new RegexPredicate(nValue);
            System.out.println(condition.test(Path.of("D:\\11111\\Username\\testDirectory2\\555.txt")));
        }
    }

    private String maskToRegexp(String s) {
        StringBuilder rsl = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            switch (aChar) {
                case '*' -> rsl.append(".*");
                case '.' -> rsl.append("\\\\.");
                case '?' -> rsl.append(".{1}");
                default -> rsl.append(aChar);
            }
        }
        return rsl.toString();
    }

    private void validation(ArgsName argsName) {
        dValue = argsName.get("d");
        nValue = argsName.get("n");
        tValue = argsName.get("t");
        oValue = argsName.get("o");

        File file = new File(dValue);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("folder or type are not valid");
        }
        if (!("name".equals(tValue) || "mask".equals(tValue) || "regex".equals(tValue))) {
            throw new IllegalArgumentException("search type are not valid");
        }
        if (!oValue.contains(".") || oValue.startsWith(".")
                || oValue.endsWith(".") || oValue.length() < 3) {
            throw new IllegalArgumentException("wrong log file name");
        }
//        try {
//            Pattern pattern = Pattern.compile(nValue);
//        } catch (PatternSyntaxException pse) {
//            throw new IllegalArgumentException("regular expression is not valid");
//        }
    }
}
