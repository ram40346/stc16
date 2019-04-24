package org.lesson02.task02;

import java.util.Scanner;

/**
 * Демонстрирует функционал {@link Generator}
 */
class GeneratorDemo {

    public static void main(String[] args) {
        System.out.println("Введите количество генерируемых значений:\n");
        try (Scanner scanner = new Scanner(System.in)) {
            Generator generator = new Generator();
            generator.generate(scanner.nextInt());
        } catch (NegativeNumberException e) {
            System.err.println(e.getMessage());
        }
    }
}
