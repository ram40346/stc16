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
public class MathBox extends ObjectBox<BigDecimal> {

    /**
     * Принимает на вход массив объектов. Не сохраняет объекты null
     * @param numbers
     */
    public MathBox(Number[] numbers) {
        this.objects = new HashSet<>();
        for (Number n : numbers) {
            if (n != null) {
                this.objects.add(new BigDecimal(n.toString()));
            }
        }
    }

    @Override
    public void addObject(BigDecimal objects) {
        super.addObject(objects);
    }

    /**
     * Метод суммирует все элементы коллекции
     * @return sum {@link BigDecimal}
     */
    public BigDecimal summator() {
        Iterator<BigDecimal> iter = objects.iterator();
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
        Iterator<BigDecimal> iterator = objects.iterator();
        while (iterator.hasNext()) {
            BigDecimal temp = iterator.next();
            dividedNumbers.add(temp.divide(new BigDecimal(divider.toString())));

        }
        objects = dividedNumbers;
    }

    /**
     * Метод удаляет элемент коллекции, если он совпадает с аргументом метода
     * @param value
     */
    public void remove(Integer value) {
        BigDecimal bigDecimalValue = new BigDecimal(value.toString());
        if(objects.contains(bigDecimalValue)) {
            objects.remove(bigDecimalValue);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName());
        result.append("[");
        Iterator<BigDecimal> iter = objects.iterator();
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
          if(!objects.contains(new BigDecimal(iter.next().toString()))) {
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
        return objects.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof MathBox) {
            MathBox mathBox = (MathBox) obj;
            if (mathBox.size() == this.size()) {
                return mathBox.containsAll(objects);

            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        int hash = objects.size();
        Iterator<BigDecimal> iter = objects.iterator();
        for (BigDecimal s : objects) {
            hash = hash + s.multiply(BigDecimal.valueOf(31)).divide(BigDecimal.valueOf(17),
                    0, RoundingMode.HALF_DOWN).intValue();
        }
        return hash;
    }
}