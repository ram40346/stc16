package org.lesson02.task03;


import java.text.Collator;
import java.util.List;

/**
 * Класс производит сортировку методом Bubble Sort
 */
public class PersonBubbleSort implements Sortable<Person> {

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
                        && ((isAgeLess(personFirst, personSecond))
                        || (isAgeEquals(personFirst, personSecond)
                        && isSwitchByName(personFirst, personSecond)))) {
                    switchValues(list, i, i + 1);
                    isSorted = false;
                } else if (personFirst.equals(personSecond)) {
                    try {
                        throwIdentityException(personFirst, personSecond);
                    } catch (PersonNotComparableException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private void throwIdentityException(Person personFirst, Person personSecond) throws PersonNotComparableException {
        throw new PersonNotComparableException(personFirst, personSecond);
    }

    private boolean isSwitchByName(Person personFirst, Person personSecond) {
        return Collator.getInstance().compare(personFirst.getName(), personSecond.getName()) > 0;
    }

    private boolean isAgeLess(Person personFirst, Person personSecond) {
        return personFirst.getAge() < personSecond.getAge();
    }

    private boolean isWomanBeforeMan(Person personFirst, Person personSecond) {
        return personFirst.getSex() == Sex.WOMAN && personSecond.getSex() == Sex.MAN;
    }

    private boolean isSexMatches(Person personFirst, Person personSecond) {
        return personFirst.getSex().equals(personSecond.getSex());
    }

    private boolean isAgeEquals(Person personFirst, Person personSecond) {
        return personFirst.getAge().equals(personSecond.getAge());
    }

    private void switchValues(List<Person> persons, int i, int j) {
        Person temporaryPerson = persons.get(i);
        persons.set(i, persons.get(j));
        persons.set(j, temporaryPerson);
    }
}
