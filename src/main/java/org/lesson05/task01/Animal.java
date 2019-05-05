package org.lesson05.task01;

import org.lesson02.task03.Person;

/**
 * Класс описывает любое животное
 */
public class Animal {

    private Integer id;
    private String name;
    private Person person;
    private Double weight;

    public Animal(Integer id, String name, Person person, Double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.person = person;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id * 31 / 17 + weight.hashCode() + person.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }


        if (obj instanceof Animal) {
            Animal animal = (Animal) obj;
            return animal.getName().equals(getName()) && animal.getPerson().equals(getPerson())
                    && animal.getWeight().equals(getWeight()) && animal.getId().equals(getId());

        }
        return false;
    }
}
