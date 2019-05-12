package org.lesson02.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.lesson02.task03.PersonCompareUtils.*;

/**
 * Класс производит сортировку методом Selection Sort
 */
public class PersonSorting implements Sortable<Person> {

    private static final Logger log = Logger.getLogger(PersonSorting.class.getName());

    private List<Person> mans;
    private List<Person> woman;

    public PersonSorting() {
        mans = new ArrayList<>();
        woman = new ArrayList<>();
    }

    @Override
    public void sort(List<Person> persons) {
        for (Person person : persons) {
            if (person.getSex() == Sex.MAN) {
                mans.add(person);
            } else {
                woman.add(person);
            }
        }
        sortSubListOfOneGender(mans);
        sortSubListOfOneGender(woman);
        persons.clear();
        persons.addAll(mans);
        persons.addAll(woman);

    }

    private void sortSubListOfOneGender(List<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            for (int j = 0; j < people.size(); j++) {
                if (i != j) {
                    Person personFirst = people.get(i);
                    Person personSecond = people.get(j);
                    if (isAgeLess(personFirst, personSecond) ||
                            (isAgeEquals(personFirst, personSecond) && isNameLess(personFirst, personSecond))) {
                        switchValues(people, i, j);
                    } else {
                        try {
                            throwIfIdentical(personFirst, personSecond);
                        } catch (PersonNotComparableException e) {
                            log.warning(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    private void switchValues(List<Person> persons, int i, int j) {
        Person temporaryPerson = persons.get(i);
        persons.set(i, persons.get(j));
        persons.set(j, temporaryPerson);
    }

    private void throwIfIdentical(Person personFirst, Person personSecond) throws PersonNotComparableException {
        if (personFirst.getAge().equals(personSecond.getAge())
                && personFirst.getName().compareTo(personSecond.getName()) == 0) {
            throw new PersonNotComparableException(personFirst, personSecond);
        }
    }
}
