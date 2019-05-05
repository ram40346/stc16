package org.lesson05.task01;

/**
 * Ошибка дублирования животного при добавлении в картотеку
 */
public class DuplicateAnimalException extends Exception{

    public DuplicateAnimalException() {
        super();
    }

    public DuplicateAnimalException(String message) {
        super(message);
    }
}
