package org.lesson08.task01;

import java.io.*;
import java.util.logging.Logger;

/*
Задание 1. Необходимо разработать класс, реализующий следующие методы:
void serialize (Object object, String file);
Object deSerialize(String file);

Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
*/

/**
 * Класс сериализует объект, и десериализует его
 */
public class SerializeDemo {

    private static final Logger log = Logger.getLogger(SerializeDemo.class.getName());
    /**
     * Метод записывает объект
     * @param object
     * @param file
     */
    public void serialize(Object object, String file) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            os.writeObject(object);
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    /**
     * Метод считывает объект
     * @param file
     * @return
     */
    public Object deSerialize(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch(IOException | ClassNotFoundException e) {
            log.warning(e.getMessage());
        }
        return null;
    }

    /**
     * Метод сереализует и десереализует объекты
     * @param args
     */
    public static void main(String[] args) {
        ClassSerialize classSerialize = new ClassSerialize(6789, 1.0, "string",
                'a', 23L, (byte) 1);

        SerializeDemo serializeDemo = new SerializeDemo();
        serializeDemo.serialize(classSerialize, "serialize.ram");
        log.info(serializeDemo.deSerialize("serialize.ram").toString());
    }
}