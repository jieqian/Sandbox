package com.sandbox.clazzloader;

import java.io.*;

/**
 * Created by qianjie on 12/14/17.
 */
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = findClass(name);
        if(clazz == null){
            return super.loadClass(name);
        }
        else {
            return clazz;
        }
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        Class<?> clazz = null;
        if (classData == null) {
            clazz = super.findLoadedClass(name);
        }
        else {
            clazz = defineClass(name, classData, 0, classData.length);
        }
        return clazz;
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            //ignore
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}
