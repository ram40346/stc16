package org.lesson10;


import static org.lesson10.Server.PORT;

public class ClientOne {
    public static String ipAddr = "localhost";

    /**
     * Создание клиент-соединения с узананными адресом и номером порта
     */
    public static void main(String[] args) {
        new ClientSettings(ipAddr, PORT);
    }
}

