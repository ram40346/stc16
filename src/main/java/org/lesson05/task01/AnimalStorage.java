package org.lesson05.task01;

import org.lesson02.task03.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс картотека животных
 */
public class AnimalStorage implements AnimalCollection {

    private HashMap<String, List<Integer>> nameIdMap;
    private HashMap<Integer, Animal> idAnimalMap;

    public AnimalStorage() {
        nameIdMap = new HashMap<>();
        idAnimalMap = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     * @param animal
     * @throws DuplicateAnimalException
     */
    @Override
    public void addAnimal(Animal animal) throws DuplicateAnimalException {
        List<Integer> integerList = nameIdMap.get(animal.getName());
        if (integerList == null) {
            fillValues(animal, new LinkedList<>());
        } else if (integerList.contains(animal.getId())) {
            throw new DuplicateAnimalException("Животное с данным идентификатором уже есть в списке.");
        } else {
            fillValues(animal, integerList);
        }
    }

    private void fillValues(Animal animal, List<Integer> integerList) {
        integerList.add(animal.getId());
        nameIdMap.put(animal.getName(), integerList);
        idAnimalMap.put(animal.getId(), animal);
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
    public void setAnimalNameById(Integer id, String name) throws DuplicateAnimalException {
        Animal animal = idAnimalMap.get(id);
        nameIdMap.get(animal.getName()).remove(id);
        animal.setName(name);
        addAnimal(animal);
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
        return nameIdMap.get(name).stream().map(id -> idAnimalMap.get(id)).collect(Collectors.toList());
    }
}
