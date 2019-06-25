
package org.lesson12.task01;


import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс вызывает ошибку outOfMemoryError c пометкой Java Heap Space
 */

public class OutOfMemoryJavaHeap {
    public static void main(String[] args) {
        int size = 1000;
        int[] array = new int[size];
        List<Object> objectList = new ArrayList<>();
        while (true) {
            for (int i = 1; i < array.length; i++) {
                int randomInt = RandomUtils.nextInt();
                array[i] = randomInt;
                objectList.add(array);
                size = size * 10;
            }
            System.out.println(array + " Free memory:" + Runtime.getRuntime().freeMemory());
        }
    }
}