package ru.job4j.io.find;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Поиск файлов в заданном каталоге, удовлетворяющих определенному условию.
 * Условия поиска: по имени, по маске, по регулярному выражению.
 * Формат: -d=D:\11111 -n=*.d?c -t=mask -o=data/findLog.txt
 * d - путь к папке в которой будем искать
 * n - имя, маска или регулярное выражение
 * t - name для поиска по имени, mask для маски, regex для регулярного выражения
 * o - файл, куда будут записаны результаты поиска
 */
public class Find {

    /**
     * dValue - путь к папке в которой будем искать
     */
    private String dValue;
    /**
     * nValue - имя, маска или регулярное выражение
     */
    private String nValue;
    /**
     * tValue - name для поиска по имени, mask для маски, regex для регулярного выражения
     */
    private String tValue;
    /**
     * oValue - файл, куда будут записаны результаты поиска
     */
    private String oValue;
    /**
     * condition - условие поиска
     */
    private Predicate<Path> condition;

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

    /**
     * Запись в файл найденных файлов
     * @param list содержит пути к найденным файлам
     */
    private void writeToFile(List<Path> list) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(oValue)))) {
            for (Path path : list) {
                writer.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * формирование условия поиска на основе входных данных
     */
    private void getPredicate() {
       if ("name".equals(tValue)) {
            condition = p -> nValue.equals(p.getFileName().toString());
        } else if ("mask".equals(tValue)) {
            String regexpString = maskToRegexp(nValue);
            condition = p -> p.getFileName()
                    .toString()
                    .matches(regexpString);
        } else {
            condition = p -> p.getFileName()
                    .toString()
                    .matches(nValue);
        }
    }

    /**
     * Преобразование маски в регулярное выражение
     * @param maskString маска, которую необходимо преобразовать в регулярное выражение
     * @return String полученное регулярное выражение
     */
    private String maskToRegexp(String maskString) {
        return maskString.replace(".", "[.]").replace("*", ".+").replace("?", ".");
    }

    /**
     * Проверка входных параметров:
     * существует ли директория, которую указал пользователь?
     * правильно ли задан параметр поиска name, mask, regex?
     * правильно ли задано имя выходного файла?
     * корректно ли записано регулярное выражение?
     * @param argsName распарсенные ключи
     */
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
        if ("regex".equals(tValue)) {
            try {
                Pattern pattern = Pattern.compile(nValue);
            } catch (PatternSyntaxException pse) {
                throw new IllegalArgumentException("regular expression is not valid");
            }
        }
    }
}
