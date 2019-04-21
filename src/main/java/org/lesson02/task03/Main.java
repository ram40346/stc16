package org.lesson02.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите количество генерируемых объектов типа Person  для сортировки");
            int personCount = scanner.nextInt();
            if (personCount < 10000) {
                System.out.printf("Минимально допустимое значение - 10000.\n " +
                        "Введено значение меньше минимально допустимого. \n" +
                        "Будет сгенерированно 10000 значений.\n");
                personCount = 10000;
            }
            for (int i = 0; i < personCount; i++) {
                personList.add(Person.generatePerson());
            }
        }

        Sortable<Person> firstAlgorithm = new PersonSorting();
       /* Sortable<Person> alternativeAlgorithm = new PersonAlternativeSorting();*/

        long startFirstAlgorithm = System.currentTimeMillis();
        firstAlgorithm.sort(personList);
        long endFirstAlgorithm = System.currentTimeMillis();

        personList.forEach(person -> System.out.println(person + "\n"));

        long processingTime = endFirstAlgorithm - startFirstAlgorithm;
        System.out.println("Время работы первого алгоритма: " + processingTime + " мс");

        /*long startAlternativeAlgorithm = System.currentTimeMillis();
        alternativeAlgorithm.sort(copyPersonList);
        long endAlternativeAlgorithm = System.currentTimeMillis();

        copyPersonList.forEach(person -> System.out.println(person + "\n"));
        processingTime = endAlternativeAlgorithm - startAlternativeAlgorithm;
        System.out.println("Время работы второго алгоритма: " + processingTime + " мс");*/
    }
}
