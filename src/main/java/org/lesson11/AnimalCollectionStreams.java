package org.lesson11;

import org.lesson02.task03.Person;
import org.lesson05.task01.Animal;
import org.lesson05.task01.AnimalCollection;
import org.lesson05.task01.DuplicateAnimalException;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalCollectionStreams implements AnimalCollection {

    private HashMap<String, List<Integer>> nameIdMap;
    private HashMap<Integer, Animal> idAnimalMap;

    public AnimalCollectionStreams() {
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
        animals.addAll(idAnimalMap.values());
        Collections.sort(animals, (o1, o2) -> {
            if (o1.getPerson().compareTo(o2.getPerson()) > 0) {
                return -1;
            } else if (o1.getPerson().compareTo(o2.getPerson()) == 0) {
                if (o1.getName().compareToIgnoreCase(o2.getName()) < 0) {
                    return -1;
                } else if (o1.getName().compareToIgnoreCase(o2.getName()) == 0 && o1.getWeight() > o2.getWeight()) {
                        return -1;
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
        Optional<Animal> optionalAnimal = Optional.ofNullable(idAnimalMap.get(id));
        nameIdMap.get(optionalAnimal
                .orElseThrow(() -> new NoSuchElementException("Объекта с id:" + id + "не существует")).getName()).remove(id);
        optionalAnimal.map(animal -> {
            animal.setName(name);
            return animal;
        });
        addAnimal(optionalAnimal.get());
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
        Optional<List<Integer>> integerList = Optional.ofNullable(nameIdMap.get(name));
        return integerList.orElseThrow(() -> new NoSuchElementException("Объектов с именем \"" + name + "\" не суцществует"))
                .stream().map(id -> idAnimalMap.get(id))
                .collect(Collectors.toList());

    }
}
