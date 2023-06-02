package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            String logStr;
            String notConnected = null;
            String connected = "";
            while ((logStr = in.readLine()) != null) {
                if (notConnected == null
                        && (logStr.startsWith("400") || logStr.startsWith("500"))) {
                    notConnected = logStr.substring(logStr.indexOf(" ") + 1);
                }
                if (notConnected != null
                        && (logStr.startsWith("200") || logStr.startsWith("300"))) {
                    connected = logStr.substring(logStr.indexOf(" ") + 1);
                    out.printf("%s;%s;%s", notConnected, connected, System.lineSeparator());
                    notConnected = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
