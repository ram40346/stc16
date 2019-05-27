package org.lesson07;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

/**
 * Класс считает факторил ключа, и результат записывает в значение
 */

@Slf4j
public class ThreadExecution {
    public static final String WARNING_INTERRUPTED = "Current thread was interrupted while waiting";
    private static final int MAX_ARRAY_LENGTH = 100;
    private static final int MAX_VALUE_NUMBERS = 200;
    private static final int TIMEOUT = 100;
    public static final String WARNING_EXECUTION = "Computation threw an exception";
    public static final String WARNING_TIMEOUT = "Wait timed out";
    private static RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
    private static Map<Integer, BigInteger> factorialsMap = new ConcurrentSkipListMap<>();

    /**
     * Выбран второй вариант решения задачи(распараллеливать вычисления для разных чисел)
     * @param args
     */

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        Map<Integer, Future<BigInteger>> futures = new HashMap<>();
        Arrays.stream(generateNumbers()).forEach(value ->
                futures.put(value, CompletableFuture.supplyAsync(() -> factorial(value), executorService)));
        futures.entrySet().forEach(entry -> {
            try {
                factorialsMap.put(entry.getKey(), entry.getValue().get(TIMEOUT, TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                log.warn(WARNING_INTERRUPTED);
            } catch (ExecutionException e) {
                log.warn(WARNING_EXECUTION);
            } catch (TimeoutException e) {
                log.warn(WARNING_TIMEOUT);
            }
        });

        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("task completed");
            } else {
                log.info("Forcing shutdown...");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.warn("ExecutionService was interrupted while waiting");
        }
        factorialsMap.forEach((integer, bigInteger) -> log.info(integer + "! : " + bigInteger));

    }

    /**
     * Метод генерирует массив случайных чисел
     * @return Массив сгенерироанных чисел
     */
    private static int[] generateNumbers() {
        int[] numbers = new int[randomDataGenerator.nextInt(INTEGER_ONE, MAX_ARRAY_LENGTH)];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = randomDataGenerator.nextInt(INTEGER_ONE, MAX_VALUE_NUMBERS);
        }
        return numbers;
    }

    /**
     * Метод высчитывает факториал числа
     * @param number
     * @return факториал числа
     */
    private static BigInteger factorial(Integer number) {
        BigInteger result = BigInteger.ONE;
        for (int i = number; i >= 1; i--) {
            if (factorialsMap.get(i)== null) {
                result = result.multiply(BigInteger.valueOf(i));
            } else {
                return result.multiply(factorialsMap.get(i));
            }
        }
        return result;
    }
}
