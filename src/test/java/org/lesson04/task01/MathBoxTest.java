package org.lesson04.task01;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathBoxTest {

    @Test
    void test_summator_result_not_floating_integer() {
        Number[] arr = new Number[]{1, 2.0, 3, 4.0f, 5L};
        MathBox box = new MathBox(arr);
        assertThat(box.summator()).isEqualTo(BigDecimal.valueOf(15.0));
    }

    @Test
    void test_summator_result_floating_integer() {
        Number[] arr = new Number[]{1, 2.0, 3, 4.5f, 5L};
        MathBox box = new MathBox(arr);
        assertThat(box.summator()).isEqualTo(BigDecimal.valueOf(15.5));
    }

    @Test
    void test_summator_removes_duplicate() {
        Number[] arr = new Number[]{1, 1, 2, 2, 3};
        MathBox box = new MathBox(arr);
        assertThat(box.summator()).isEqualTo(BigDecimal.valueOf(6));
    }

    @Test
    void summator_skip_null() {
        Number[] arr = new Number[]{1, 1, 2, 2, null, 3};
        new MathBox(arr);
    }

    @Test
    void splitter_divide() {
        Number[] arr = new Number[]{2.2, 4, 6, 8, 10};
        MathBox box = new MathBox(arr);
        box.splitter(2);
        assertThat(box.summator()).isEqualTo(BigDecimal.valueOf(15.1));
    }

    @Test
    void remove() {
        Number[] arr = new Number[]{2.2, 4, 6, 8, 10};
        MathBox box = new MathBox(arr);
        box.remove(6);
        assertThat(box.summator()).isEqualTo(BigDecimal.valueOf(24.2));
    }

    @Test
    void math_box_equals() {
        Number[] arr = new Number[]{2.2, 4, 6, 8, 10};
        MathBox mathBox = new MathBox(arr);
        MathBox mathBox2 = new MathBox(arr);
        assertEquals(mathBox, mathBox2);
    }

    @Test
    void math_box_hashcode() {
        Number[] arr = new Number[]{2.2, 4, 6};
        MathBox mathBox = new MathBox(arr);
        MathBox mathBox2 = mathBox;
        assertEquals(mathBox.hashCode(), mathBox2.hashCode());
    }

    @Test
    void math_box_containce() {
        Number[] arr = new Number[]{2.2, 4, 6};
        MathBox mathBox = new MathBox(arr);
        assertTrue(mathBox.containsAll(Arrays.asList(arr)));
    }

    @Test
    void math_box_size() {
        Number[] arr = new Number[]{2.2, 4, 6};
        MathBox mathBox = new MathBox(arr);
        assertEquals(arr.length, mathBox.size());
    }
}
