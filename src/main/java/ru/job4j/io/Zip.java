package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private Path directory;
    private String exclude;
    private Path output;

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
