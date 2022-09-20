package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Поиск файлов с заданным расширением в заданной папке, которые
 * передаются через аргументы запуска.
 */
public class Search {

    public static void main(String[] args) throws IOException {
        if (!validation(args)) {
            throw new IllegalArgumentException("folder or type are not valid");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Осуществляет поиск файлов удовлетворяющих условию
     * @param root Path - папка, где ищем файлы
     * @param condition Predicate - условие поиска
     * @return список Path - в котором все Path удовлетворяющие условию
     * @throws IOException
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Валидация входных параметров
     * @param args массис String - первый элемент это директория поиска, а второй это расширение файла
     * @return boolean true - если переданные аргументы валидны
     */
    private static boolean validation(String[] args) {
        if (args.length != 2) {
            return false;
        }
        Path folder = Paths.get(args[0]);
        if (!Files.exists(folder) || !Files.isDirectory(folder)) {
            return false;
        }
        return args[1].startsWith(".") && args[1].length() >= 2;
    }
}
