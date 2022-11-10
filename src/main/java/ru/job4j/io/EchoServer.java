package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String answer = "What";
                    String inMsg = in.readLine();
                    System.out.println(inMsg);
                    if (inMsg != null) {
                        if (inMsg.contains("msg=Hello")) {
                            answer = "Hello";
                        } else if (inMsg.contains("msg=Exit")) {
                            answer = "Exit";
                            System.out.println("Завершение работы сервера");
                            server.close();
                        }
                    }
                    out.write(answer.getBytes());
                    out.flush();
                }
            }
        }
    }
}
