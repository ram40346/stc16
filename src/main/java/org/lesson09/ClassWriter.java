package org.lesson09;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.lesson09.Constants.*;

/**
 * Создаёт файл и записывает в него ввод с консоли
 */
public class ClassWriter {

    public static final Logger log = Logger.getLogger(ClassWriter.class.getName());

    private ClassWriter() {
    }

    public static void classWriter() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Scanner s = new Scanner(System.in);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILEPATH))) {
            bw.write(IMPORT);
            bw.write(CLASS_NAME);
            bw.write(METHOD_SIGNATURE);

            while (true) {
                String line = s.nextLine();
                if (line.equals(EMPTY)) {
                    break;
                }
                bw.write(line);
            }
            bw.write(SPACING_AND_BRACE);

        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }
}

