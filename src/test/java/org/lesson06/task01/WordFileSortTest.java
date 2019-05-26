package org.lesson06.task01;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class WordFileSortTest {

    @Test
    void test_sort_demonstration() {
        WordFileSort testSort = new WordFileSort();
        List<String> s = testSort.sort("src/test/resources/notSorted.txt");
        System.out.println(s);
    }

    @Test
    void test_sort_success() {
        List<String> sortedExpected = new ArrayList<>();
        sortedExpected.add("funds");
        sortedExpected.add("gregarious");
        sortedExpected.add("sketchy");
        sortedExpected.add("Strenuous");
        sortedExpected.add("Suspicious");

        List<String> notSorted = new ArrayList<>();
        notSorted.add("Strenuous");
        notSorted.add("Suspicious");
        notSorted.add("funds");
        notSorted.add("gregarious");
        notSorted.add("sketchy");
        notSorted.add("sketchy");

        File file = new File("src/test/resources/testNotSorted.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            file.deleteOnExit();
            FileWriter fileWriter = new FileWriter(file);
            for (String s : notSorted) {
                fileWriter.write(s);
                fileWriter.write(" ");
            }
            fileWriter.flush();
            WordFileSort testSort = new WordFileSort();
            List<String> sortedActual = testSort.sort("src/test/resources/testNotSorted.txt");
            assertArrayEquals(sortedExpected.toArray(), sortedActual.toArray());

            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}