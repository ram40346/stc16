package org.lesson10;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Поток чтения сообщений с сервера
 */
@Slf4j
class ReadMessage extends Thread {
    private ClientSettings clientSettings;

    public ReadMessage(ClientSettings clientSettings) {
        this.clientSettings = clientSettings;
    }

    @Override
    public void run() {
        String serverMSG;
        try {
            while (true) {
                serverMSG = clientSettings.getInputSocket().readLine();
                if (serverMSG.equals("quit")) {
                    clientSettings.downService();
                    break;
                }
                System.out.println(serverMSG);
            }
        } catch (IOException e) {
            clientSettings.downService();
            log.info(e.getMessage());
        }
    }
}

