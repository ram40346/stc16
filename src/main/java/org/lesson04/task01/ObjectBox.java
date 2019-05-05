package org.lesson04.task01;
/*
Создать класс ObjectBox, который будет хранить коллекцию Object.
        У класса должен быть метод addObject, добавляющий объект в коллекцию.
        У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
        Должен быть метод dump, выводящий содержимое коллекции в строку.*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Коллекция объектов {@link Object}
 */
public class ObjectBox<T> {

    protected Collection<T> objects;

    public ObjectBox() {
        objects = new ArrayList();
    }

    /**
     * Добавляет объект в коллекцию
     * @param objects
     */
    public void addObject(T objects) {
        this.objects.add(objects);
    }

    /**
     * Удаляет обект из коллекции
     * @param object
     */
    public void deleteObject(Object object) {
        if (objects.contains(object)) {
            objects.remove(object);
        }
    }

    /**
     * Печатает элементы коллекции через ","
     */
    public void dump() {
        Iterator<T> iter = objects.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iter.hasNext()) {
            stringBuilder.append(iter.next());
            if (iter.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        System.out.print(stringBuilder);
    }

    /**
     * Возвращает размер коллекции
     * @return количество объектов
     */
    public int size() {
        return objects.size();
    }
}
