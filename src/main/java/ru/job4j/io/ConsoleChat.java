package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация консольного чата с ботом.
 */
public class ConsoleChat {
    /**
     * String "закончить" - команда от пользователя остановить программу
     */
    private static final String OUT = "закончить";
    /**
     * String "стоп" - команда остановить ответы бота
     */
    private static final String STOP = "стоп";
    /**
     * String "продолжить" - команда разрешить ответы бота
     */
    private static final String CONTINUE = "продолжить";
    /**
     * String path - путь к логфайлу диалога
     */
    private final String path;
    /**
     * String botAnswers - путь к файлу с ответами бота
     */
    private final String botAnswers;
    /**
     * List<String> logList - временное хранение лога всей беседы
     */
    private final List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Основная логика программы. Чтение сообщений пользователя и рандомный ответ
     * на них от бота (если он разрешен). При выходе запись всей беседы в файл.
     * @throws IOException
     */
    public void run() throws IOException {
        List<String> botAnswersList = readPhrases();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean stopFlag = false;
            String userConsole = reader.readLine();
            while (!OUT.equals(userConsole)) {
                if (STOP.equals(userConsole)) {
                    stopFlag = true;
                }
                logList.add(userConsole);
                if (!stopFlag || CONTINUE.equals(userConsole)) {
                    int lineIndex = (int) (Math.random() * botAnswersList.size());
                    String botConsole = botAnswersList.get(lineIndex);
                    logList.add(botConsole);
                    System.out.println(botConsole);
                    stopFlag = false;
                }
                userConsole = reader.readLine();
            }
            saveLog(logList);
        }
    }

    private boolean validation(String testPath) {
        return Files.exists(Path.of(testPath)) && Files.isRegularFile(Path.of(testPath));
    }

    /**
     * Чтение всех возможных вариантов ответов бота в лист
     * @return List<String> - с ответами бота
     */
    private List<String> readPhrases() {
        if (!validation(botAnswers)) {
            throw new IllegalArgumentException("file not found");
        }
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Сохранение лога беседы в файл
     * @param log List<String> - запись всей беседы
     */
    private void saveLog(List<String> log)  {
        try (PrintWriter logWriter = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String s : log) {
                logWriter.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("dialog.txt", "botAnswers.txt");
        cc.run();
    }
}