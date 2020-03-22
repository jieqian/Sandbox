package com.sandbox.algorithm.bracket;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Sandbox {
    public static String brackets = "{[(()())]}";
    public static Map<Character,Character> bracketLRMapping = new HashMap<>();
    static {
        bracketLRMapping.put(')','(');
        bracketLRMapping.put(']','[');
        bracketLRMapping.put('}','{');
    }

    public static void main(String []args){
        System.out.println(isValidBrackets(brackets));
    }

    public static boolean isValidBrackets(String brackets){
        Stack<Character> stack = new Stack<>();
        int length = brackets.length();
        for (char c : brackets.toCharArray()) {
            if(bracketLRMapping.containsKey(c)){ // right bracket
                if (stack.empty()){
                    return false;
                }
                if (bracketLRMapping.get(c) != stack.pop()){
                    return false;
                }
            } else { // left bracket
                stack.push(c);
            }
        }
        return stack.empty();
    }
}
