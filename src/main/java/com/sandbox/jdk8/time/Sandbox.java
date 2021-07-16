package com.sandbox.jdk8.time;

import java.time.*;

public class Sandbox {

    public static void main(String[] args) {
        // Instant
        //下面的代码表示从1970-01-01 00:00:00开始后两分钟的10万纳秒的时刻
        Instant instant = Instant.ofEpochSecond(120, 100000);
        System.out.println("============================== Instant ==============================");
        System.out.println(instant);

        // Duration
        LocalDateTime from = LocalDateTime.of(2017, Month.JANUARY, 5, 10, 7, 0);    // 2017-01-05 10:07:00
        LocalDateTime to = LocalDateTime.of(2017, Month.FEBRUARY, 5, 10, 7, 0);     // 2017-02-05 10:07:00
        Duration duration = Duration.between(from, to);     // 表示从 2017-01-05 10:07:00 到 2017-02-05 10:07:00 这段时间
        long days = duration.toDays();              // 这段时间的总天数
        long hours = duration.toHours();            // 这段时间的小时数
        long minutes = duration.toMinutes();        // 这段时间的分钟数
        long seconds = duration.getSeconds();       // 这段时间的秒数
        long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
        long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数
        System.out.println("============================== Duration ==============================");
        System.out.println("Days of duration: " + days);
        System.out.println("Hours of duration: " + hours);
        System.out.println("Minutes of duration: " + minutes);
        System.out.println("Seconds of duration: " + seconds);
        System.out.println("MilliSeconds of duration: " + milliSeconds);
        System.out.println("NanoSeconds of duration: " + nanoSeconds);

        // Period
        LocalDate startTime = LocalDate.of(2017, 1, 5);
        LocalDate endTime = LocalDate.of(2017, 1, 8);
        Period period = Period.between(startTime, endTime);
        System.out.println("============================== Period ==============================");
        System.out.println("Days of period: " + period.getDays());



    }
}
