package com.sandbox.jdk8.time.localdatetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class Sandbox {

    public static void main(String[] args) {
        LocalDateTime ldt1 = LocalDateTime.of(2017, Month.JANUARY, 4, 17, 23, 52);
        System.out.println("ldt1 => " + ldt1);

        LocalDate localDate = LocalDate.of(2017, Month.JANUARY, 4);
        LocalTime localTime = LocalTime.of(17, 23, 52);
        LocalDateTime ldt2 = localDate.atTime(localTime);
        System.out.println("ldt2 => " + ldt2);
    }
}
