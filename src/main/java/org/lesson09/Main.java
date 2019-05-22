package org.lesson09;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Демонстрация функционала
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scan.classWriter();
        Compiler.compile(new File("C:\\Users\\Рамиль\\IdeaProjects\\stc16\\src\\test\\resources\\org.lesson09\\WorkerImpl.java"));
        ClassLoader parentClassLoader = Worker.class.getClassLoader();

        WorkerClassLoader workerClassLoader = new WorkerClassLoader(parentClassLoader);
        Class worker = workerClassLoader.loadClass("org.lesson09.WorkerImpl");
        worker.getMethod("doWork").invoke(worker.newInstance(), null);
    }
}
