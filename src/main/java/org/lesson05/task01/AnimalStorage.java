package org.lesson05.task01;

import org.lesson02.task03.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс картотека животных
 */
public class AnimalStorage implements AnimalCollection {

    private HashMap<Integer, String> idNameMap;
    private HashMap<Integer, Animal> idAnimalMap;

    public AnimalStorage() {
        idNameMap = new HashMap<>();
        idAnimalMap = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     * @param animal
     * @throws DuplicateAnimalException
     */
    @Override
    public void addAnimal(Animal animal) throws DuplicateAnimalException {
        if (idNameMap.get(animal.getId()) == null) {
            idNameMap.put(animal.getId(), animal.getName());
            idAnimalMap.put(animal.getId(), animal);
        } else {
            throw new DuplicateAnimalException("Животное с данным идентификатором уже есть в списке.");
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Animal> sort() {
        List<Animal> animals = new ArrayList<>();
        idAnimalMap.values().stream().forEach(animal -> animals.add(animal));
        Collections.sort(animals, (o1, o2) -> {
            if (o1.getPerson().compareTo(o2.getPerson()) > 0) {
                return -1;
            } else if (o1.getPerson().compareTo(o2.getPerson()) == 0) {
                if (o1.getName().compareToIgnoreCase(o2.getName()) < 0) {
                    return -1;
                } else if (o1.getName().compareToIgnoreCase(o2.getName()) == 0) {
                    if (o1.getWeight() > o2.getWeight()) {
                        return -1;
                    }
                }
            }
            return 1;
        });
        return animals;
    }

    /**
     * {@inheritDoc}
     * @param id
     * @param name
     */
    @Override
    public void setAnimalNameById(Integer id, String name) {
        Animal animal = idAnimalMap.get(id);
        animal.setName(name);
        idNameMap.replace(id, name);
    }

    /**
     * {@inheritDoc}
     *
     * @param id
     * @param weight
     */
    @Override
    public void setAnimalWeightById(Integer id, Double weight) {
        idAnimalMap.get(id).setWeight(weight);
    }

    /**
     * {@inheritDoc}
     *
     * @param id
     * @param person
     */
    @Override
    public void setAnimalPersonById(Integer id, Person person) {
        idAnimalMap.get(id).setPerson(person);
    }

    /**
     * {@inheritDoc}
     *
     * @param name
     * @return
     */
    @Override
    public List<Animal> findByName(String name) {
        return idNameMap.entrySet().stream()
                .filter(idNamePair -> name.equals(idNamePair.getValue()))
                .map(idNamePair -> idAnimalMap.get(idNamePair.getKey()))
                .collect(Collectors.toList());
    }
}
