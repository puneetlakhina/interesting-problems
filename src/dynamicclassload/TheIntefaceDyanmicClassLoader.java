package dynamicclassload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TheIntefaceDyanmicClassLoader extends ClassLoader {

    public TheIntefaceDyanmicClassLoader(ClassLoader parentClassLoader) {
        super(parentClassLoader);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.equals("dynamicclassload.TheInterfaceImpl")) {
            return super.loadClass(name);
        } else {
            Class<?> klass = findLoadedClass(name);
            if(klass != null) {
                return klass;
            }
            String fileName = "build/classes/dynamicclassload/TheInterfaceImpl.class";
            InputStream input = null;
            try {
                File f = new File(fileName);
                System.out.println(f.getAbsolutePath());
                input = new FileInputStream(f);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int data = input.read();
                while (data != -1) {
                    buffer.write(data);
                    data = input.read();
                }

                byte[] classData = buffer.toByteArray();

                return defineClass("dynamicclassload.TheInterfaceImpl", classData, 0, classData.length);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
}
