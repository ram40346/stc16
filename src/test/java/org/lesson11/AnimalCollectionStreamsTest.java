package org.lesson11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lesson02.task03.Person;
import org.lesson02.task03.Sex;
import org.lesson05.task01.Animal;
import org.lesson05.task01.AnimalCollection;
import org.lesson05.task01.DuplicateAnimalException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AnimalCollectionStreamsTest {

    private AnimalCollection actualAnimals;
    private Person person1 = new Person(12, Sex.WOMAN, "Маша");
    private Person person2 = new Person(21, Sex.WOMAN, "Валя");
    private Person person3 = new Person(12, Sex.MAN, "Миша");
    private Person person4 = new Person(21, Sex.MAN, "Ваня");

    @BeforeEach
    void setUp() throws Exception {
        actualAnimals = new AnimalCollectionStreams();
        actualAnimals.addAnimal(new Animal(1, "Белка", person1, 1.0));
        actualAnimals.addAnimal(new Animal(2, "Стрелка", person1, 1.0));
        actualAnimals.addAnimal(new Animal(3, "Стрелка", person1, 1.3));
        actualAnimals.addAnimal(new Animal(4, "Белка", person2, 1.0));
        actualAnimals.addAnimal(new Animal(5, "Стрелка", person2, 1.0));
        actualAnimals.addAnimal(new Animal(6, "Стрелка", person2, 1.3));
        actualAnimals.addAnimal(new Animal(7, "Боб", person3, 1.3));
        actualAnimals.addAnimal(new Animal(8, "Мурка", person3, 1.3));
        actualAnimals.addAnimal(new Animal(9, "Пушинка", person4, 1.3));
        actualAnimals.addAnimal(new Animal(10, "Пушинка", person4, 2.0));
    }

    @Test
    void test_add_animal_duplicate_id() {
        assertThrows(DuplicateAnimalException.class, () -> actualAnimals.
                addAnimal(new Animal(2, "Стрелка", new Person(20, Sex.WOMAN, "Регина"), 1.0)));
    }

    @Test
    void test_add_animal_success() {
        Animal animal = new Animal(11, "Гена", new Person(20, Sex.WOMAN, "Регина"), 1.0);
        assertDoesNotThrow(() -> actualAnimals.addAnimal(animal));
        assertArrayEquals(Arrays.asList(animal).toArray(), actualAnimals.findByName("Гена").toArray());
    }

    @Test
    void test_sort() {
        List<Animal> sortedAnimalsExpected = new LinkedList<>();
        sortedAnimalsExpected.add(new Animal(10, "Пушинка", person4, 2.0));
        sortedAnimalsExpected.add(new Animal(9, "Пушинка", person4, 1.3));

        sortedAnimalsExpected.add(new Animal(7, "Боб", person3, 1.3));
        sortedAnimalsExpected.add(new Animal(8, "Мурка", person3, 1.3));

        sortedAnimalsExpected.add(new Animal(4, "Белка", person2, 1.0));
        sortedAnimalsExpected.add(new Animal(6, "Стрелка", person2, 1.3));
        sortedAnimalsExpected.add(new Animal(5, "Стрелка", person2, 1.0));


        sortedAnimalsExpected.add(new Animal(1, "Белка", person1, 1.0));
        sortedAnimalsExpected.add(new Animal(3, "Стрелка", person1, 1.3));
        sortedAnimalsExpected.add(new Animal(2, "Стрелка", person1, 1.0));


        List<Animal> animalList = actualAnimals.sort();
        assertArrayEquals(sortedAnimalsExpected.toArray(), animalList.toArray());
    }

    @Test
    void test_setAnimalNameById() throws DuplicateAnimalException{
        actualAnimals.setAnimalNameById(2, "Вася");
        assertEquals(actualAnimals.findByName("Вася").size(), 1);
    }

    @Test
    void test_setAnimalNameByIdThrowsException() {
        assertThrows(NoSuchElementException.class, () -> actualAnimals.setAnimalNameById(22, "Вася"));
    }

    @Test
    void test_setAnimalWeightById() {
        double weight = 1.0;
        actualAnimals.setAnimalWeightById(7, weight);
        assertEquals(weight, actualAnimals.findByName("Боб").get(0).getWeight());
    }

    @Test
    void test_setAnimalPersonById() {
        actualAnimals.setAnimalPersonById(7, person4);
        assertEquals(person4, actualAnimals.findByName("Боб").get(0).getPerson());
    }

    @Test
    void test_findByName() {
        List<Animal> expectedList = new LinkedList<>();

        expectedList.add(new Animal(3, "Стрелка", person1, 1.3));
        expectedList.add(new Animal(2, "Стрелка", person1, 1.0));
        expectedList.add(new Animal(6, "Стрелка", person2, 1.3));
        expectedList.add(new Animal(5, "Стрелка", person2, 1.0));

        List<Animal> animalList = actualAnimals.findByName("Стрелка");
        assertEquals(expectedList.size(), animalList.size());
        assertTrue(animalList.containsAll(expectedList));
    }

    @Test
    void test_findByName_throws_exception() {
        assertThrows(NoSuchElementException.class, () -> actualAnimals.findByName("Джекоб"));
    }
}