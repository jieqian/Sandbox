package com.sandbox.algorithm.lru.doublelinkednode;

public class Sandbox {
    public static void main(String[] args) {
        LRUCache<String,Integer> cache = new LRUCache<>(2);
        cache.put("one",1);
        cache.put("two",2);
        cache.get("one");
        cache.put("three",3);
        System.out.println(cache.get("two"));
    }
}
