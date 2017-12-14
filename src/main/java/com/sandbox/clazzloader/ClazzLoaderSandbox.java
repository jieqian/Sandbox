package com.sandbox.clazzloader;

/**
 * Created by qianjie on 12/14/17.
 */
public class ClazzLoaderSandbox {
    public static void main(String[] args) {
        printClassLoaderTree();
        ClazzIdentity clazzIdentity = new ClazzIdentity();
        clazzIdentity.testClassIdentity();
    }

    public static void printClassLoaderTree(){
        ClassLoader loader = ClazzLoaderSandbox.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
