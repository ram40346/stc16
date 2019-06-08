package org.lesson10;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * В классе описана возможности клиента
 */
@Getter
@Slf4j
class ClientSettings {

    private Socket socket;
    private BufferedReader inputSocket;
    private BufferedWriter outputSocket;
    private BufferedReader inputUser;
    private String nickname;

    /**
     * Для создания необходимо принять адрес и номер порта
     * @param clientAddress
     * @param port
     */
    public ClientSettings(String clientAddress, int port) {
        try {
            this.socket = new Socket(clientAddress, port);
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            new ReadMessage(this).start();
            new WriteMessage(this).start();
        } catch (IOException e) {
            log.error(e.getMessage());
            ClientSettings.this.downService();
        }
    }

    /**
     * Метод для ввода имени, и отсылка эхо с приветсвием на сервер
     */
    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname = inputUser.readLine();
            outputSocket.write("Hello " + nickname + "\n");
            System.out.println("Enter your message");
            outputSocket.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Закрытие сокета
     */
    void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                inputSocket.close();
                outputSocket.close();
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

}