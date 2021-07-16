package com.sandbox.sizeof.test;

import com.sandbox.sizeof.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

public class Sandbox {
    public static void main(String[] args) {
        User user = new User("qian".toCharArray(),38);
        System.out.println(RamUsageEstimator.sizeOf(user));

        System.out.println(ClassLayout.parseInstance(user).toPrintable());

        System.out.println(user.hashCode());

    }
}
