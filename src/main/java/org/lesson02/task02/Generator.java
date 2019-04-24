package org.lesson02.task02;

import org.apache.commons.math3.random.RandomDataGenerator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

/**
 * Класс генерирует n чисел и выводит в консоль те числа,
 * квадрат квадратного корня которого равен целой части сгенерированного числа
 */
class Generator {

    private static final double MIN_DOUBLE = 1D;
    private static final double MAX_DOUBLE = 10000D;
    private static final int POWER = 2;

    /**
     * Класс генерирует n чисел и выводит в консоль те числа,
     * квадрат квадратного корня которого равен целой части сгенерированного числа
     * @param n количество генерируемых значений
     * @throws NegativeNumberException когда передано отрицательное знгачение количества генерируемых значений
     * либо сгенерированио отрицательное число
     */
    void generate(int n) throws NegativeNumberException {
        if (n > INTEGER_ZERO) {
            RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
            for (int y = INTEGER_ZERO; y < n; y++) {
                printIfSquarePowerEquals(randomDataGenerator);
            }
        } else {
            throw new NegativeNumberException("Невозможно сгенерировать отрицательное количество чисел");
        }
    }

    private void printIfSquarePowerEquals(RandomDataGenerator randomDataGenerator) throws NegativeNumberException {
        double generatedNumber = randomDataGenerator.nextUniform(MIN_DOUBLE, MAX_DOUBLE);
        if (generatedNumber< INTEGER_ZERO) {
            throw new NegativeNumberException();
        }
        double sqrt = sqrt(generatedNumber);

        double fractionalPart = sqrt % INTEGER_ONE;
        double integralPart = sqrt - fractionalPart;
        double square = pow(integralPart, POWER);
        double wholeGenerated = generatedNumber - generatedNumber % INTEGER_ONE;
        if (wholeGenerated == square) {
            System.out.println(generatedNumber);
        }
    }
}
