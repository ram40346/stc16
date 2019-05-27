package org.lesson09;

import lombok.experimental.UtilityClass;

@UtilityClass
class Constants {
    static final String CLASS_PATH = "org.lesson09.WorkerImpl";
    static final String DIRECTORY = "C:\\Users\\Рамиль\\IdeaProjects\\stc16\\src\\test\\resources\\org.lesson09";
    static final String FILEPATH = DIRECTORY + "\\WorkerImpl.java";
    static final String LINK = DIRECTORY + "\\WorkerImpl.class";

    static final String IMPORT = "package org.lesson09; \n\n";
    static final String CLASS_NAME = "public class WorkerImpl implements Worker {\n";
    static final String METHOD_SIGNATURE = "\tpublic void doWork() {\n\t\t";
    static final String SPACING_AND_BRACE = "\n\t}\n\n}";
}
