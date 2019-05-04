package org.lesson04.task01;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Класс реализует коллекцию Set
 */

public class MathBox {

    private Set<BigDecimal> numbers;

    /**
     *
     * @param numbers
     */

    public MathBox(Number[] numbers) {
        this.numbers = new HashSet<>();
        for (Number n : numbers) {
            if (n != null) {
                this.numbers.add(new BigDecimal(n.toString()));
            }
        }
    }

    /**
     * Метод суммирует все элементы коллекции
     * @return sum {@link BigDecimal}
     */

    public BigDecimal summator() {
        Iterator<BigDecimal> iter = numbers.iterator();
        BigDecimal sum = BigDecimal.ZERO;
        while (iter.hasNext()) {
            sum = sum.add(new BigDecimal(iter.next().toString()));
        }
        return sum;
    }

    /**
     * Метод выполняет поочерёдное деление элементов коллекции на делитель, являющийся аргументом метода,
     * и перезаписывает хранящиеся в объкте данные на результат деления.
     * @param divider
     */
    public void splitter(Number divider) {
        Set<BigDecimal> dividedNumbers = new HashSet<>();
        Iterator<BigDecimal> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            BigDecimal temp = iterator.next();
            dividedNumbers.add(temp.divide(new BigDecimal(divider.toString())));

        }
        numbers = dividedNumbers;
    }

    /**
     * Метод удаляет элемент коллекции, если он совпадает с аргументом метода
     * @param value
     */
    public void remove(Integer value) {
        numbers.remove(new BigDecimal(value.toString()));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName());
        result.append("[");
        Iterator<BigDecimal> iter = numbers.iterator();
        while (iter.hasNext()) {
            result.append(iter.next());
            if (iter.hasNext()) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Проверяет, находятся ли все элементы в коллекции
     * @param numberCollection
     * @return
     */
    public boolean containsAll(Collection numberCollection) {
        Iterator iter = numberCollection.iterator();
        while (iter.hasNext()) {
          if(!numbers.contains(new BigDecimal(iter.next().toString()))) {
            return false;
          }
        }
        return true;
    }

    /**
     * Возвращает размер массива
     * @return
     */
    public int size() {
        return numbers.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof MathBox) {
            MathBox mathBox = (MathBox) obj;
            if (mathBox.size() == this.size()) {
                return mathBox.containsAll(numbers);

            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        int hash = numbers.size();
        Iterator<BigDecimal> iter = numbers.iterator();
        for (BigDecimal s : numbers) {
            hash = hash + s.multiply(BigDecimal.valueOf(31)).divide(BigDecimal.valueOf(17),
                    0, RoundingMode.HALF_DOWN).intValue();
        }
        return hash;
    }
}