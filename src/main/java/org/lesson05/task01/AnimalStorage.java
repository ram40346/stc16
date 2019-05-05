package org.lesson05.task01;

import org.lesson02.task03.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс картотека животных
 */
public class AnimalStorage implements AnimalCollection {

    private HashMap<Integer, String> idNameMap;
    private Hashtable<String, HashMap<Integer,Animal>> nameAnimalMap;

    public AnimalStorage() {
        idNameMap = new HashMap<>();
        nameAnimalMap = new Hashtable<>();
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
            if (nameAnimalMap.get(animal.getName()) == null) {
                nameAnimalMap.put(animal.getName(), new HashMap<>());
            }
            nameAnimalMap.get(animal.getName()).put(animal.getId() ,animal);
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
        nameAnimalMap.values().stream().forEach(animalsMap -> animals.addAll(animalsMap.values()));
        Collections.sort(animals, (o1, o2) -> {
            if (o1.getPerson().getName().compareTo(o2.getPerson().getName()) < 0) {
                return -1;
            } else if (o1.getPerson().getName().compareTo(o2.getPerson().getName()) == 0) {
                if (o1.getName().compareTo(o2.getName()) < 0) {
                    return -1;
                } else if (o1.getName().compareTo(o2.getName()) == 0) {
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
        Animal animal = nameAnimalMap.get(idNameMap.get(id)).get(id);
        nameAnimalMap.remove(name);

        animal.setName(name);
        idNameMap.replace(id, name);
        if (nameAnimalMap.get(name) == null) {
            HashMap<Integer, Animal> map = new HashMap<>();
            map.put(id, animal);
            nameAnimalMap.put(name, map);
        }
    }

    /**
     * {@inheritDoc}
     * @param id
     * @param weight
     */
    @Override
    public void setAnimalWeightById(Integer id, Double weight) {
        nameAnimalMap.get(idNameMap.get(id)).get(id).setWeight(weight);
    }

    /**
     * {@inheritDoc}
     * @param id
     * @param person
     */
    @Override
    public void setAnimalPersonById(Integer id, Person person) {
        nameAnimalMap.get(idNameMap.get(id)).get(id).setPerson(person);
    }

    /**
     * {@inheritDoc}
     * @param name
     * @return
     */
    @Override
    public List<Animal> findByName(String name) {
        return nameAnimalMap.get(name).values().stream().collect(Collectors.toList());
    }
}
