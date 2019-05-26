package org.lesson06.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Класс читает файл и производит сортировку.
 */
public class WordFileSort {

    private static final Logger log = Logger.getLogger(WordFileSort.class.getName());
    public static final String REGEXP = "[ \\. \\, \\s\\\"\\'\\%\\-\\_\\:\\;\\*\\(\\)\\t\\r\\n]+";

    /**
     * Сортирует слова по алфавиту.
     *
     * @param filePath путь к файлу.
     * @return Отсортированный список.
     */
    public List<String> sort(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Set<String> resultList = new HashSet<>();
            reader.lines().forEach(line -> {
                String[] words = line.split(REGEXP);
                resultList.addAll(Arrays.asList(words));
            });
            return resultList.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
        return Collections.emptyList();
    }
}
