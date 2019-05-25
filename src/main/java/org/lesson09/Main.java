package org.lesson09;

import java.io.File;

/**
 * Демонстрация функционала
 */
public class Main {
    static final String CLASS_PATH = "org.lesson09.WorkerImpl";
    static final String DIRECTORY = "org.lesson09";
    static final String FILEPATH = DIRECTORY + "\\WorkerImpl.java";
    static final String LINK = DIRECTORY + "\\WorkerImpl.class";

    public static void main(String[] args) throws Exception {
        ClassWriter.classWriter();
        Compiler.compile(new File(FILEPATH));
        ClassLoader parentClassLoader = Worker.class.getClassLoader();

        WorkerClassLoader workerClassLoader = new WorkerClassLoader(parentClassLoader);
        Class worker = workerClassLoader.loadClass(CLASS_PATH);
        worker.getMethod("doWork").invoke(worker.newInstance(), null);
    }
}
