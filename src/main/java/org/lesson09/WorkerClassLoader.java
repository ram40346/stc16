package org.lesson09;

import java.io.*;
import java.util.logging.Logger;

import static org.lesson09.Constants.CLASS_PATH;
import static org.lesson09.Constants.LINK;

/**
 * Загрусчик класса WorkerImpl
 */

public class WorkerClassLoader extends ClassLoader {

    private static final Logger log = Logger.getLogger(WorkerClassLoader.class.getName());

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals(CLASS_PATH)) {
            byte[] classData = new byte[]{};
            try (InputStream inputStream = new FileInputStream(new File(LINK));
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){

                int data = inputStream.read();
                while (data!=-1){
                    byteArrayOutputStream.write(data);
                    data = inputStream.read();
                }

                classData = byteArrayOutputStream.toByteArray();

                byteArrayOutputStream.flush();
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
            return defineClass(name, classData, 0, classData.length);
        } else {
            return super.loadClass(name);
        }
    }
}
