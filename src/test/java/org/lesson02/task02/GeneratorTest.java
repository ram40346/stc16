package org.lesson02.task02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneratorTest {

    @Test
    void generateThrowsNegativeNumberExceptionTest() {
        Generator generator = new Generator();
        assertThrows(NegativeNumberException.class, () -> generator.generate(-4));
    }
}