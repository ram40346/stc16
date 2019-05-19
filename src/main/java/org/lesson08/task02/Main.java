package org.lesson08.task02;

import org.lesson08.task01.SerializeDemo;

import java.awt.*;
import java.util.logging.Logger;

/**
 * Демонстрация сериализации сложных объектов.
 */
public class Main {

    public static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        SerializeDemo serializeDemo = new SerializeDemo();

        Figure figure = new Figure(12, 14, Color.BLACK);
        serializeDemo.serialize(figure, "figure.ram");
        log.info(serializeDemo.deSerialize("figure.ram").toString());
    }
}
