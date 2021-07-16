package com.sandbox.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class SoftRef {
    public static void main(String[] args) throws InterruptedException {
        int count = 3;
        SoftReference<TestObject>[] softReferences = new SoftReference[count];
        List<Test2> test2s = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            softReferences[i] = new SoftReference(new TestObject("test-" + i));
//            test2s.add(softReferences[i].get().test2);
//        }
//        for (int i = 0; i < count; i++) {
//            TestObject object = softReferences[i].get();
//            if (object == null) {
//                System.out.println(i + "testObject=========null");
//            } else {
//                System.out.println(object.name);
//            }
//            Test2 test2 = test2s.get(i);
//            if (test2 == null) {
//                System.out.println(i + "test2=========null");
//            } else {
//                System.out.println(test2.name);
//            }
//        }

        ExecutorService threadPool = newFixedThreadPool(5);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                SoftReference<TestObject> softReference = new SoftReference(new TestObject("test-" + finalI));
                ThreadLocal<SoftReference<TestObject>> local = new ThreadLocal<>();
                local.set(softReference);
//                ThreadLocal<TestObject> local = new ThreadLocal<>();
//                local.set(new TestObject("test-" + finalI) );
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TestObject object = local.get().get();
//                TestObject object = local.get();
                if (object == null) {
                    System.out.println(finalI + "testObject=========null---"+Thread.currentThread().getId());
                } else {
                    System.out.println(object.name+"----"+Thread.currentThread().getId());
                }
            });
        }

    }


}

class TestObject {
    public String name;
    public byte[] values;
    public Test2 test2;

    public TestObject(String name) {
        System.gc();
        this.name = name;
        this.values = new byte[1024 * 1024 * 1024];
        this.test2 = new Test2(name);
    }
}

class Test2 {
    public String name;

    public Test2(String name) {
        this.name = "test2" + name;
    }
}
