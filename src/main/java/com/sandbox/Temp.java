package com.sandbox;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Temp {
    private static final DateTimeFormatter SERVICER_VERSION_PREFIX_PATTEN = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
//        List<Integer> list = Arrays.asList(1,2,3);
//        list.add(11);
//        list.stream().forEach(System.out::println);
        Temp t= new Temp();
//        System.out.println(t.dump());
        System.out.println(t.buildServiceVersion("2020032314213400002"));

        List<String> list = Arrays.asList("3","2","4");
        System.out.println(String.join(",",list));
        System.out.println(Integer.parseInt("2020032314213400002".substring(14)));
    }

    private String buildServiceVersion(){
        return buildServiceVersion("");
    }

    private String buildServiceVersion(String lastVersion){
        String prefix = LocalDateTime.now().format(SERVICER_VERSION_PREFIX_PATTEN);
        int autoIncreamentVersion = 1;
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(lastVersion)){
            autoIncreamentVersion = Integer.parseInt(lastVersion.substring(14)) + 1;
        }
        return prefix + org.apache.commons.lang3.StringUtils.leftPad(String.valueOf(autoIncreamentVersion),5,"0");
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
