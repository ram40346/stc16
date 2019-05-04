package org.lesson04.task01;
/*
Создать класс ObjectBox, который будет хранить коллекцию Object.
        У класса должен быть метод addObject, добавляющий объект в коллекцию.
        У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
        Должен быть метод dump, выводящий содержимое коллекции в строку.*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ObjectBox {

    private List objects;

    public ObjectBox() {
        objects = new ArrayList();
    }

    public void addObject(Object objects) {
        this.objects.add(objects);
    }

    public void deleteObject(Object object) {
        if (objects.contains(object)) {
            objects.remove(object);
        }
    }

    public void dump() {
        Iterator<Object> iter = objects.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iter.hasNext()) {
            stringBuilder.append(iter.next());
            if (iter.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        System.out.println(stringBuilder.append(iter.hasNext()));
    }

}
