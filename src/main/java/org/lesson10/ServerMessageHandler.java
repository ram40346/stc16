package org.lesson10;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * Серверный поток
 */
@Slf4j
class ServerMessageHandler extends Thread {

    private Socket socket;

    private BufferedReader readInSocket;
    private BufferedWriter writeOutSocket;

    public ServerMessageHandler(Socket socket) throws IOException {
        this.socket = socket;
        readInSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writeOutSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            word = readInSocket.readLine();
            writeOutSocket.write(word + "\n");
            writeOutSocket.flush();
            while (true) {
                word = readInSocket.readLine();
                if (word.equals("stop")) {
                    this.downService();
                    break;
                }
                System.out.println("Echoing: " + word);
                for (ServerMessageHandler vr : Server.serverList) {
                    vr.send(word);
                }
            }
        } catch (IOException | NullPointerException e) {
            this.downService();
            log.info(e.getMessage());
        }
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                readInSocket.close();
                writeOutSocket.close();
                for (ServerMessageHandler vr : Server.serverList) {
                    if (vr.equals(this)) {
                        vr.interrupt();
                    }
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void send(String msg) throws IOException {
        writeOutSocket.write(msg + "\n");
        writeOutSocket.flush();
    }
}
