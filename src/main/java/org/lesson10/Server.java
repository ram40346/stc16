package org.lesson10;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Класс запускает сервер
 */
@Slf4j
public class Server {

    public static final int PORT = 2134;
    public static LinkedList<ServerMessageHandler> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Сервер запущен");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerMessageHandler(socket));
                } catch (IOException e) {
                    log.error(e.getMessage());
                    socket.close();
                }
            }
        } finally {
            System.out.println("Сервер закрыт!");
            server.close();
        }
    }
}