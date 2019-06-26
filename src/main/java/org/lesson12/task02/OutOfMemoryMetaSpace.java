package org.lesson12.task02;

import javassist.CannotCompileException;
import javassist.ClassPool;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс вызывает ошибку outOfMemoryError c пометкой Metaspace.
 * Для получениея ошибки требуется выставить настройки JVM "-XX:MaxMetaspaceSize=20m".
 */
@Slf4j
public class OutOfMemoryMetaSpace {

    static ClassPool classPool = ClassPool.getDefault();

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 1000000; i++) {
                Class classLoad = classPool.makeClass(i + " OutOfMemoryErrorMetaspace").toClass();
                System.out.println(classLoad.getName());
            }
        } catch (CannotCompileException e) {
            log.error(e.getMessage());
        }
    }
}

