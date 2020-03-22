package com.sandbox.jar;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Sandbox {
    public static void main(String[] args) throws IOException {
        String jarFile = "/Users/qianjie/work/LunaWorkspace/sandbox/target/sandbox-0.0.1-SNAPSHOT.jar";
        JarFile jar = new JarFile(jarFile);
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.getName().endsWith("pom.properties")) {
                Properties props = new Properties();
                props.load(jar.getInputStream(entry));
                System.out.println(props.getProperty("version","no branch"));
                break;
            } else {
                continue;
            }
        }
    }
}
