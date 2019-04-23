package org.lesson02.task01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HelloWorldTest {

    @Test
    void callToNullTest() {
        assertThrows(NullPointerException.class, HelloWorld::tryToCallNull);
    }

    @Test
    void callToArrayIndexOutOfBoundExceptionTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class, HelloWorld::tryToCallWrongIndex);
    }

    @Test
    void callToThrowMyExceptionTest() {
        assertThrows(MyException.class, HelloWorld::tryToThrowMyException);
    }

    @Test
    void callToThrowMyRunTimeExceptionTest() {
        assertThrows(RuntimeException.class, HelloWorld::tryToThrowMyRunTimeException);
    }
}
