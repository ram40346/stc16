package org.lesson06.task02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextGeneratorTest {
    TextGenerator textGenerator;

    @BeforeEach
    void setUp() {
        textGenerator = new TextGenerator();
    }

    @Test
    void test_generate_words() {
        textGenerator.getFiles("srс/test/resources/lesson06", 2,16, textGenerator.generateWords(), 50);
    }
}