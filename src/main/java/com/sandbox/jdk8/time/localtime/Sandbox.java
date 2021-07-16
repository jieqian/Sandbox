package com.sandbox.jdk8.time.localtime;

import java.time.LocalTime;

public class Sandbox {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(17, 23, 52);     // 初始化一个时间：17:23:52
        int hour = localTime.getHour();                     // 时：17
        int minute = localTime.getMinute();                 // 分：23
        int second = localTime.getSecond();                 // 秒：52

        System.out.println(localTime);
        System.out.println("Hour is " + hour);
        System.out.println("Minute is " + minute);
        System.out.println("Second is " + second);
    }
}
