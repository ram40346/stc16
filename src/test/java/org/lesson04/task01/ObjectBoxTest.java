package org.lesson04.task01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjectBoxTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    ObjectBox objectBox = new ObjectBox();


    @BeforeEach
    public void setUpStreams() {
        objectBox.addObject("one");
        objectBox.addObject("two");
        objectBox.addObject("three");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void addObject() {
        assertEquals(objectBox.size(), 3);
        objectBox.addObject("five");
        assertEquals(objectBox.size(), 4);
    }

    @Test
    void deleteObject() {
        assertEquals(objectBox.size(), 3);
        objectBox.deleteObject("three");
        assertEquals(objectBox.size(), 2);
    }

    @Test
    void dump() {
        objectBox.dump();
        assertEquals("one, two, three", outContent.toString());
    }
}