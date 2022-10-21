package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Простая утилита для архивирования файлов и директорий
 * формат аргументов для запуска: пример -d=c:\projects\job4j\ -e=.class -o=project.zip
 * d - путь к директории, которая будет архивироваться
 * е - тип файлов, которые архивировать не надо
 * о - название архива
 */
public class Zip {

    /**
     * directory - путь к директории, которая будет архивироваться
     */
    private Path directory;
    /**
     * exclude - тип файлов, которые архивировать не надо
     */
    private String exclude;
    /**
     * output - название архива
     */
    private Path output;

    /**
     * Пакует в архив переданные файлы
     * @param sources List c файлами, которые будем архивировать
     * @param target File архив
     */
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Пакует в архив один переданный файл
     * @param source File - который надо запаковать
     * @param target File - архив
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<File> convertPathToFile() throws IOException {
        List<Path> pathList = Search.search(directory, p -> !p.toString().endsWith(exclude));
        return pathList.stream().map(Path::toFile).collect(Collectors.toList());
    }

    /**
     * Проверка входных аргументов. Если аргументы заданы неправильно
     * кидается соответствующее исключение с пояснением
     * @param args - массив строк. Переданные аргументы
     */
    private void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        ArgsName arguments = ArgsName.of(args);
        String d = arguments.get("d");
        String e = arguments.get("e");
        String o = arguments.get("o");
        directory = Path.of(d);
        if (!Files.exists(directory)) {
            throw new IllegalArgumentException("directory does not exist");
        }
        if (!e.contains(".") || e.endsWith(".") || !o.endsWith(".zip")) {
            throw new IllegalArgumentException("invalid file extension format");
        }
        exclude = e.substring(e.indexOf("."));
        output = Path.of(o);
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validateArgs(args);
        zip.packFiles(zip.convertPathToFile(), zip.output.toFile());
    }
}
