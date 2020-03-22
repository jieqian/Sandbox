package com.sandbox;

import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Temp {
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
//        List<Integer> list = Arrays.asList(1,2,3);
//        list.add(11);
//        list.stream().forEach(System.out::println);
        Temp t= new Temp();
        System.out.println(t.dump());
    }

    public String dump(){
        String dump = null;
        try {
            if (dump.equals("")){
                System.out.println("equals");
            }
        } finally {
            System.out.println("finally");
        }

        System.out.println("processing");

        return "end";
    }
}
