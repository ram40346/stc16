package org.lesson06.task02;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Создать генератор текстовых файлов, работающий по следующим правилам:

Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
Слово состоит из 1<=n2<=15 латинских букв
Слова разделены одним пробелом
Предложение начинается с заглавной буквы
Предложение заканчивается (.|!|?)+" "
Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предйложени. В конце абзаца стоит разрыв строки и перенос каретки.


Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability), который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 */
public class TextGenerator {

    private RandomDataGenerator generator = new RandomDataGenerator();
    private Map<Double, String> wordsProbabilityMap = new HashMap<>();

    private static final Integer MAX_WORDS_ARRAY = 1000;
    private static final Integer MIN_WORDS_ARRAY = 1;

    private static final Integer MAX_SENTENCE_LENGHT = 15;
    private static final Integer MIN_SENTENCE_LENGHT = 1;

    private static final Integer MAX_WORD_LENHGT = 16;
    private static final Integer MIN_WORD_LENGHT = 1;

    private static final Integer MAX_PARAGRAPH_LENGHT = 20;
    private static final Integer MIN_PARAGRAPH_LENGHT = 1;



    public List<File> getFiles(String path, int filesCount, int size, String[] words, int probability) {


        return null;
    }

    protected String[] generateWords() {
        Integer lenght = generator.nextInt(MIN_WORDS_ARRAY, MAX_WORDS_ARRAY);
        String[] words = new String[lenght];
        for (int i = 0; i < words.length; i++) {
            words[i] = RandomStringUtils.randomAlphabetic(MIN_WORD_LENGHT, MAX_WORD_LENHGT);

        }
        return words;
    }
}
