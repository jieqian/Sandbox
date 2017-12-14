package com.sandbox.clazzloader;

import java.lang.reflect.Method;

/**
 * Created by qianjie on 12/14/17.
 */
public class ClazzIdentity {
    public void testClassIdentity() {
        String classDataRootPath = "/Users/qianjie/work/LunaWorkspace/sandbox/target/classes";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.sandbox.clazzloader.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2); // Class instances are identical if they have same fully qualified name and same class loader
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClazzIdentity clazzIdentity = new ClazzIdentity();
        clazzIdentity.testClassIdentity();
    }
}
