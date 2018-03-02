package com.sandbox.jdk8.util.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by qianjie on 3/1/18.
 */
public class OptionalSandbox {
    public static void main(String[] args) {
        Optional<String> gender = Optional.of("MALE");
        String answer1 = "Yes";
        String answer2 = null;

        System.out.println("Non-Empty Optional:" + gender);
        System.out.println("Non-Empty Optional: Gender value : " + gender.get());
        System.out.println("Empty Optional: " + Optional.empty());

        System.out.println("ofNullable on Non-Empty Optional: " + Optional.ofNullable(answer1));
        System.out.println("ofNullable on Empty Optional: " + Optional.ofNullable(answer2).isPresent());

        // java.lang.NullPointerException
//        System.out.println("ofNullable on Non-Empty Optional: " + Optional.of(answer2));

        List<String> list = null;
        Optional<List<String>> listOptional = Optional.ofNullable(list);
        List<String> copyList = listOptional.orElseGet(()-> {return new ArrayList<>();});
        System.out.println(copyList.size());
        List<String> anotherCopyList = listOptional.orElse(new ArrayList<>());
        System.out.println(anotherCopyList.size());
    }
}
