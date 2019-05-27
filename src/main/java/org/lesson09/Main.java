package org.lesson09;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

import static org.lesson09.Constants.CLASS_PATH;
import static org.lesson09.Constants.FILEPATH;


/**
 * Демонстрация функционала
 */
public class Main {


    public static void main(String[] args) throws Exception {
        ClassWriter.classWriter();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, new File(FILEPATH).getPath());

        WorkerClassLoader workerClassLoader = new WorkerClassLoader();
        Class worker = workerClassLoader.loadClass(CLASS_PATH);
        worker.getMethod("doWork").invoke(worker.newInstance(), null);
    }
}
