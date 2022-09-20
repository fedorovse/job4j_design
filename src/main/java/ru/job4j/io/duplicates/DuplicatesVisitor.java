package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Поиск и сохранение всех файлов
 * Поиск дубликатов файлов
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /**
     * filesMap хранит все файлы и пути к ним
     */
    private final Map<FileProperty, List<Path>> filesMap = new HashMap<>();

    /**
     * Поиск всех файлов в заданном каталоге и запись их в Map
     * @param file Path путь к каталогу где осуществляется поиск
     * @param attrs BasicFileAttributes
     * @return FileVisitResult
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        filesMap.putIfAbsent(fileProperty, new ArrayList<>());
        filesMap.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    /**
     * Оставляет в Мар только файлы у которых есть дубликаты
     * @return Map<FileProperty, List<Path>> который содержит только файлы
     * у которых есть дубликаты и пути к ним
     */
    public Map<FileProperty, List<Path>> getDuplicatesMap() {
        Map<FileProperty, List<Path>> duplicatesMap = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> entry : filesMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                duplicatesMap.put(entry.getKey(), entry.getValue());
            }
        }
        return duplicatesMap;
    }

    /**
     * Вывод в консоль дубликотов файлов и путей к ним в формате:
     * Имя файла - размер
     * 1.путь
     * 2.путь
     * и т.д.
     */
    public void printDuplicates() {
        Map<FileProperty, List<Path>> duplicatesMap = getDuplicatesMap();
        for (Map.Entry<FileProperty, List<Path>> entry : duplicatesMap.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(System.out::println);
        }
    }
}
