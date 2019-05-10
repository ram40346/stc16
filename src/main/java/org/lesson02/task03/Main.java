package org.lesson02.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Принимает из консоли количество генерируемых объектов. Сортирует двумя способами.
 */

public class Main {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите количество генерируемых объектов типа Person  для сортировки");
            int personCount = scanner.nextInt();
            if (personCount < 10000) {
                System.out.print("Минимально допустимое значение - 10000.\n " +
                        "Введено значение меньше минимально допустимого. \n" +
                        "Будет сгенерированно 10000 значений.\n");
                personCount = 10000;
            }
            for (int i = 0; i < personCount; i++) {
                personList.add(PersonGenerator.generatePerson());
            }
        }

        Sortable<Person> firstAlgorithm = new PersonSorting();
        Sortable<Person> alternativeAlgorithm = new PersonBubbleSort();

        List<Person> copyPersonList = new ArrayList<>(personList);

        long startFirstAlgorithm = System.currentTimeMillis();
        firstAlgorithm.sort(personList);
        long endFirstAlgorithm = System.currentTimeMillis();

        personList.forEach(person -> System.out.println(person + "\n"));

        long processingTime = endFirstAlgorithm - startFirstAlgorithm;

        long startAlternativeAlgorithm = System.currentTimeMillis();
        alternativeAlgorithm.sort(copyPersonList);
        long endAlternativeAlgorithm = System.currentTimeMillis();

        copyPersonList.forEach(person -> System.out.println(person + "\n"));
        long processingTime2 = endAlternativeAlgorithm - startAlternativeAlgorithm;

        System.out.println("Время работы первого алгоритма: " + processingTime + " мс");
        System.out.println("Время работы второго алгоритма: " + processingTime2 + " мс");
    }
}
