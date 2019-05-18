package org.lesson02.task03;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

/**
 * Генерирует объект {@link Person}
 *
 */
public class PersonGenerator {

    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_AGE = 100;

    private PersonGenerator() {
    }


    /**
     * Генерирует объекты {@link Person}
     *
     * @return Сгенерированный объект
     */

    public static Person generatePerson() {
        SecureRandom randomizer = new SecureRandom();
        Sex[] sexes = Sex.class.getEnumConstants();
        return new Person(randomizer.nextInt(MAX_AGE), sexes[randomizer.nextInt(sexes.length)],
                RandomStringUtils.randomAlphabetic(randomizer.nextInt(MAX_NAME_LENGTH)));
    }
}
