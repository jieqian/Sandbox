package com.sandbox;

import java.util.Random;

public class RandomUtils {
	private static final Random R = new Random();

    /**
     * 按照一定的概率
     *
     * @param rate 例如 0.04f 4%的概率
     * @return
     */
    public static boolean shouldHappen(float rate) {
        return R.nextFloat() <= rate;
    }

    public static int randomIntRange(int min, int max) {
        return R.nextInt(Math.abs(max - min) + 1) + Math.min(min, max);
    }

    public static void main(String[] args) {
    	float v = R.nextFloat();
    	System.out.println(v);
    	
//    	int counter = 0;
//        for (int i = 0; i < 1000; i++) {
//            if (RandomUtils.shouldHappen(0.04f)) {
//                System.out.println(i);
//                counter++;
//            }
//        }
//        System.out.println("happened times: " + counter);

//        System.out.println("*****");
//
//        for (int i = 0; i < 10; i++) {
//            if (RandomUtils.shouldHappen(1.0f)) {
//                System.out.println(i);
//            }
//        }
//
//        System.out.println("*****");
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(randomIntRange(2,7));
//        }
    }

    /**
     * 可以指定小数概率的随机整数, 例如 1.5 ~ 5.8 当随机出来是1的则在有50%的概率为2, 随机处理是5则80%的概率是6
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机值
     */
    public static int randomIntRange(float min, float max) {
        float num = randomFloat(min, max);
        int digit = (int) num;
        float rate = num - digit;
        return digit + (R.nextFloat() <= rate ? 1 : 0);
    }

    public static float randomFloat(float min, float max) {
        return R.nextFloat() * Math.abs(max - min) + Math.min(min, max);
    }
}
