package org.lesson05.task01;

import org.lesson02.task03.Person;

import java.util.List;

public interface AnimalCollection {
    /**
     * Добавляет объект в картотеку
     * @param animal
     * @throws DuplicateAnimalException
     */
    void addAnimal(Animal animal) throws DuplicateAnimalException;

    /**
     * Сортирует объекты
     * @return
     */
    List<Animal> sort();

    /**
     * Устанавливает имя по идентификатору
     * @param id
     * @param name
     */
    void setAnimalNameById(Integer id, String name) throws DuplicateAnimalException;

    /**
     * Устанавливает вес по идентификатору
     * @param id
     * @param weight
     */
    void setAnimalWeightById(Integer id, Double weight);

    /**
     * Устанавливает хозяина животного по идентификатору
     * @param id
     * @param person
     */
    void setAnimalPersonById(Integer id, Person person);

    /**
     * Ищет животных по имени
     * @param name
     * @return
     */
    List<Animal> findByName(String name);

}
