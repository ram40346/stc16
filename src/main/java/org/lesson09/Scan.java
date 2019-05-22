package org.lesson09;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Создаёт файл и записывает в него ввод с консоли
 */
public class Scan {

    public static final Logger log = Logger.getLogger(Scan.class.getName());

    private static final String DIRECTORY = "C:\\Users\\Рамиль\\IdeaProjects\\stc16\\src\\test\\resources\\org.lesson09";
    private static final String FILEPATH = DIRECTORY + "\\WorkerImpl.java";
    private static final String IMPORT = "package org.lesson09; \n\n";
    private static final String CLASS_NAME = "public class WorkerImpl implements Worker {\n";
    private static final String METHOD_SIGNATURE = "\tpublic void doWork() {\n\t\t";
    private static final String SPACING_AND_BRACE = "\n\t}\n\n}";

    private Scan() {
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

