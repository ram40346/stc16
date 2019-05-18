package org.lesson02.task03;

import java.util.List;
import java.util.logging.Logger;

import static org.lesson02.task03.PersonCompareUtils.*;


/**
 * Класс производит сортировку методом Bubble Sort
 */
public class PersonBubbleSort implements Sortable<Person> {

    private static final Logger log = Logger.getLogger(PersonBubbleSort.class.getName());

    @Override
    public void sort(List<Person> list) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                Person personFirst = list.get(i);
                Person personSecond = list.get(i + 1);
                if (isWomanBeforeMan(personFirst, personSecond)) {
                    switchValues(list, i, i + 1);
                    isSorted = false;
                } else if (isSexMatches(personFirst, personSecond)
                        && ((isAgeMore(personFirst, personSecond))
                        || (isAgeEquals(personFirst, personSecond)
                        && isNameMore(personFirst, personSecond)))) {
                    switchValues(list, i, i + 1);
                    isSorted = false;
                } else if (personFirst.equals(personSecond)) {
                    try {
                        throwIdentityException(personFirst, personSecond);
                    } catch (PersonNotComparableException e) {
                        log.warning(e.getMessage());
                    }
                }
            }
        }
    }

    private void throwIdentityException(Person personFirst, Person personSecond) throws PersonNotComparableException {
        throw new PersonNotComparableException(personFirst, personSecond);
    }

    private void switchValues(List<Person> persons, int i, int j) {
        Person temporaryPerson = persons.get(i);
        persons.set(i, persons.get(j));
        persons.set(j, temporaryPerson);
    }
}
