package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void whenUnavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("300 11:02:02");
        }

        File target = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder str = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(str::append);
        }

        assertThat(str.toString())
                .isEqualTo("10:57:01;10:59:01;"
                        + "11:01:02;11:02:02;"
                        + "10:57:01;11:02:02;");
    }

}