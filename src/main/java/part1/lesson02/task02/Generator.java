package part1.lesson02.task02;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Класс генерирует n чисел и выводит в консоль те числа,
 * квадрат квадратного корня которого равен целой части сгенерированного числа
 */
public class Generator {

    /**
     * Класс генерирует n чисел и выводит в консоль те числа,
     * квадрат квадратного корня которого равен целой части сгенерированного числа
     * @param n количество генерируемых значений
     * @throws NegativeNumberException когда передано отрицательное знгачение количества генерируемых значений
     * либо сгенерированио отрицательное число
     */
    public void generate(int n) throws NegativeNumberException {
        if (n > 0) {
            try {
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                for (int y = 0; y < n; y++) {
                    printIfSquarePowerEquals(random);
                }
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Неверный алгоритм генерации");
            } catch (NegativeNumberException e) {
                throw e;
            }
        } else {
            throw new NegativeNumberException("Невозможно сгенерировать отрицательное количество чисел");
        }
    }

    private void printIfSquarePowerEquals(SecureRandom random) throws NegativeNumberException {
        double generatedNumber = random.nextDouble();
        if (generatedNumber<0) {
            throw new NegativeNumberException();
        }
        double sqrt = sqrt(generatedNumber);
        double square = pow(sqrt,2);
        if (Math.floor(generatedNumber) == square) {
            System.out.println(generatedNumber);
        }
    }
}
