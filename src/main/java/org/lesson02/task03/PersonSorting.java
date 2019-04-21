package org.lesson02.task03;

import java.util.ArrayList;
import java.util.List;

public class PersonSorting implements Sortable<Person>{

    private List<Person> mans;
    private List<Person> woman;

    public PersonSorting() {
        mans = new ArrayList<>();
        woman = new ArrayList<>();
    }

    @Override
    public void sort(List<Person> persons) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getSex() == Sex.MAN) {
                mans.add(persons.get(i));
            } else {
                woman.add(persons.get(i));
            }
        }
        try {
            sortSubListOfOneGender(mans);
            sortSubListOfOneGender(woman);
            persons.clear();
            persons.addAll(mans);
            persons.addAll(woman);
        } catch (PersonNotComparableException e){
            System.err.println(e.getMessage());
        }

    }

    private void sortSubListOfOneGender(List<Person> people) throws PersonNotComparableException {
        for (int i = 0 ; i < people.size() - 1; i++){
            for (int j = 0; j < people.size(); j++) {
                if (i != j) {
                    if (isAgeLess(people.get(i), people.get(j)) || isSwitchByName(people.get(i), people.get(j))) {
                        switchValues(people, i, j);
                    } else {
                        try {
                            throwIfIdentical(people.get(i), people.get(j));
                        } catch (PersonNotComparableException e) {
                        }
                    }
                }
            }
        }
    }

    private boolean isSwitchByName(Person personFirst, Person personSecond) {
        return personFirst.getAge().equals(personSecond.getAge())
                && personFirst.getName().compareTo(personSecond.getName()) < 0;
    }

    private boolean isAgeLess(Person personFirst, Person personSecond) {
        return personFirst.getAge() > personSecond.getAge();
    }

    private void switchValues(List<Person> persons, int i, int j) {
        Person temporaryPerson =  persons.get(i);
        persons.set(i, persons.get(j));
        persons.set(j, temporaryPerson);
    }

    private void throwIfIdentical(Person personFirst, Person personSecond) throws PersonNotComparableException {
        if (personFirst.getAge().equals(personSecond.getAge())
                && personFirst.getName().compareTo(personSecond.getName()) == 0) {
            throw new PersonNotComparableException("Совпадение");
        }
    }
}
