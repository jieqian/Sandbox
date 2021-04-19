package com.sandbox.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakRef {
    public static void main(String[] args) throws Exception {
        ReferenceQueue<WeakReferenceObject> queue = new ReferenceQueue<>();
        WeakReferenceObject object = new WeakReferenceObject();

        PhantomReference<WeakReferenceObject> reference = new PhantomReference<>(object,queue);
//        WeakReference<WeakReferenceObject> reference = new WeakReference<>(object,queue);
        object = null;
        System.out.println("before gc is enqueued : " + reference.isEnqueued());
        System.out.println("before gc queue : " + queue.remove(100));
        System.out.println("before gc referent : " + reference.get());
        System.gc();
        System.out.println("============================= GC ===========================");
        Thread.sleep(500);
        System.out.println("after gc is enqueued : " + reference.isEnqueued());
        System.out.println("after gc queue : " + queue.remove(100));
        System.out.println("after gc referent : " + reference.get());
    }

    private static class WeakReferenceObject {

        @Override
        public String toString() {
            return "WeakReferenceObject";
        }
    }
}
