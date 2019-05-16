package org.lesson06.task02;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.math.NumberUtils.*;

/**
 * Генерирует текст по заданным условиям
 */

public class TextGenerator {

    public static final Logger log = Logger.getLogger(TextGenerator.class.getName());

    private final RandomDataGenerator generator;
    private static final Integer MAX_WORDS_ARRAY = 1000;
    private static final Integer MAX_SENTENCE_LENGHT = 15;
    private static final Integer MAX_WORD_LENHGT = 16;
    private static final Integer MAX_PARAGRAPH_LENGHT = 20;
    private static final String FILINAME = "/File%s.txt";

    public TextGenerator() {
        generator = new RandomDataGenerator();
    }

    /**
     * Сохраняет сгенерированный текст в файлы.
     *
     * @param path        путь к папке, где будут генерироваться файлы
     * @param filesCount  количество файлов
     * @param size        размер файла выраженный в обзацах
     * @param words       массив возможных слов используемых при генерации
     * @param probability пороговая вероятность вхождения слова в предложение(1/probability)
     * @return Коллекция сгенерированных фалов
     */

    public List<File> getFiles(String path, int filesCount, int size, String[] words, int probability) {
        List<File> files = new LinkedList<>();
        for (int i = 0; i < filesCount; i++) {
            File file = new File(path + String.format(FILINAME, i));
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new PrintWriter(
                            file))) {
                for (int j = 0; j < size; j++) {
                    bufferedWriter.write(generateParagraph(words, probability));
                }
                files.add(file);
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
        }

        return files;
    }

    /**
     * Генерирует параграф, из переданных предложений.
     *
     * @param words       возможные предложения
     * @param probability вероятность слова в предложение
     * @return сгенерированные параграфы
     */

    private String generateParagraph(String[] words, int probability) {
        Integer paragraphLenght = generator.nextInt(INTEGER_ONE, MAX_PARAGRAPH_LENGHT);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paragraphLenght; i++) {
            builder.append(generateSentence(words, probability));
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * Генерируем возможные слова
     *
     * @return возвращает сгенерир
     */
    public String[] generateWords() {
        Integer lenght = generator.nextInt(INTEGER_ONE, MAX_WORDS_ARRAY);
        String[] words = new String[lenght];
        for (int i = 0; i < words.length; i++) {
            words[i] = RandomStringUtils.randomAlphabetic(INTEGER_ONE, MAX_WORD_LENHGT).toLowerCase();
        }
        return words;
    }

    private String generateSentence(String[] words, int probability) {
        Map<Double, String> probabilityOfWords = new TreeMap<>();
        Arrays.stream(words).forEach(word -> probabilityOfWords.put(generator.nextUniform(DOUBLE_ZERO, DOUBLE_ONE), word));
        Integer lengthSentence = generator.nextInt(INTEGER_ONE, MAX_SENTENCE_LENGHT);
        Double probabilityThreshold = 1.0 / probability;
        List<String> strings = probabilityOfWords
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() >= probabilityThreshold)
                .limit(lengthSentence)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        String[] delimeter = new String[]{" ", ", "};
        String[] sign = new String[]{"! ", ". ", "? "};
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        String firstWord = iterator.next();
        char[] chars = firstWord.toCharArray();
        chars[0] = Character.toUpperCase(firstWord.charAt(0));
        builder.append(chars);
        while (iterator.hasNext()) {
            builder.append(delimeter[generator.nextInt(INTEGER_ZERO, delimeter.length - INTEGER_ONE)]).append(iterator.next());
        }
        return builder.append(sign[generator.nextInt(INTEGER_ZERO, sign.length - INTEGER_ONE)]).toString();
    }
}
