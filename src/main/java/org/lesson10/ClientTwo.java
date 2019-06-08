package org.lesson10;

import static org.lesson10.ClientOne.ipAddr;
import static org.lesson10.Server.PORT;

public class ClientTwo {

    /**
     * Создание клиент-соединения с узананными адресом и номером порта
     */
    public static void main(String[] args) {
        new ClientSettings(ipAddr, PORT);
    }
}