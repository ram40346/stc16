package org.lesson09;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

/**
 * Загрусчик класса WorkerImpl
 */
public class WorkerClassLoader extends ClassLoader {

    private static final Logger log = Logger.getLogger(WorkerClassLoader.class.getName());
    private static final String NAME_PATH = "org.lesson09.WorkerImpl";
    private static final String LINK = "file:\\C:\\Users\\Рамиль\\IdeaProjects\\stc16\\src\\test\\resources\\org.lesson09\\WorkerImpl.class";

    protected WorkerClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals(NAME_PATH)) {
            byte[] classData = new byte[]{};
            try {
                URL url = new URL(LINK);
                URLConnection urlConnection = url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int data = inputStream.read();
                while (data!=-1){
                    byteArrayOutputStream.write(data);
                    data = inputStream.read();
                }
                inputStream.close();

                classData = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
            return defineClass(name, classData, 0, classData.length);
        } else {
            return super.loadClass(name);
        }
    }
}
