package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileWriter(target))) {
            String logStr;
            String notConnected;
            String connected;
            while ((logStr = in.readLine()) != null) {
                if (logStr.startsWith("400") || logStr.startsWith("500")) {
                    notConnected = logStr.substring(logStr.indexOf(" ") + 1);
                    while (logStr.startsWith("400") || logStr.startsWith("500")) {
                        logStr = in.readLine();
                        if (logStr == null) {
                            throw new IllegalArgumentException(
                                    String.format("%s contains only disconnect time", source)
                            );
                        }
                    }
                    connected = logStr.substring(logStr.indexOf(" ") + 1);
                    out.printf("%s;%s;%s", notConnected, connected, System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("analizySorce.txt", "analizeTarget.txt");
    }
}
