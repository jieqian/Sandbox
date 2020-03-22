package com.sandbox.algorithm.anagram;

import java.util.HashMap;
import java.util.Map;

public class Sandbox {
    public static void main(String[] args) {
        String anagram1 = "actt";
        String anagram2 = "cat";
        System.out.println(equals(anagram1,anagram2));
    }

    public static boolean equals(String a1, String a2){
        Map<Character,Integer> m1 = new HashMap<>();
        Map<Character,Integer> m2 = new HashMap<>();
        for (char c : a1.toCharArray()) {
            Integer count = m1.get(c);
            if (count == null) {
                m1.put(c,1);
            } else {
                m1.put(c,++count);
            }
        }

        for (char c : a2.toCharArray()) {
            Integer count = m2.get(c);
            if (count == null) {
                m2.put(c,1);
            } else {
                m2.put(c,++         count);
            }
        }

        for (Map.Entry<Character, Integer> entry : m1.entrySet()) {
            char key = entry.getKey();
            int m1CharCount = entry.getValue();
            Integer m2CharCount = m2.get(key);
            if (m2CharCount==null){
                return false;
            } else if (m2CharCount.equals(m1CharCount) == false){
                return false;
            }
        }

        return true;
    }
}
