package org.lesson10;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Поток отправляющий сообщения приходящие с консоли на сервер
 */
@Slf4j
public class WriteMessage extends Thread {

    private ClientSettings clientSettings;
    private Date time;
    private String dataTime;
    private SimpleDateFormat dt1;
    public WriteMessage(ClientSettings clientSettings) {
        this.clientSettings = clientSettings;
    }

    @Override
    public void run() {
        while (true) {
            String userWord;
            try {
                time = new Date();
                dt1 = new SimpleDateFormat("HH:mm:ss");
                dataTime = dt1.format(time);
                userWord = clientSettings.getInputUser().readLine();
                if (userWord.equals("quit")) {
                    clientSettings.getOutputSocket().write("quit" + "\n");
                    clientSettings.downService();
                    break;
                } else {
                    clientSettings.getOutputSocket().write("(" + dataTime + ") "
                            + clientSettings.getNickname() + ": " + userWord + "\n");
                }
                clientSettings.getOutputSocket().flush();
            } catch (IOException e) {
                log.error(e.getMessage());
                clientSettings.downService();
            }
        }
    }
}
