package com.sandbox.jdk8.time.localdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Sandbox {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2017, 1, 4);     // 初始化一个日期：2017-01-04
        int year = localDate.getYear();                     // 年份：2017
        Month month = localDate.getMonth();                 // 月份：JANUARY
        int dayOfMonth = localDate.getDayOfMonth();         // 月份中的第几天：4
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();     // 一周的第几天：WEDNESDAY
        int length = localDate.lengthOfMonth();             // 月份的天数：31
        boolean leapYear = localDate.isLeapYear();          // 是否是闰年

        System.out.println("Year is " + year);
        System.out.println("Month is " + month);
        System.out.println("Day of Month is " + dayOfMonth);
        System.out.println("Day of Week is " + dayOfWeek);
        System.out.println("Length of Month is " + length);
        System.out.println("LeapYear is " + leapYear);

        LocalDate now = LocalDate.now();
        System.out.println("Current Date is " + now);

        String strDate = "2017-01-05";
        LocalDate dateFromStr = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Date parsing from string: " + dateFromStr);

    }

}
