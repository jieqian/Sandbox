package com.sandbox;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Temp {

    static int a = 5;
    static int b = 7;

//    static int[] array = {1,1,0,1,1};
    static int[] array = {0,1,1,0};

    public static void main(String[] args) {
        System.out.println("a->" + a + "\n" + "b->" + b);
        a=a^b;
        System.out.println("=========="+(a&(b-1)));
        b=a^b;
        a=a^b;
        System.out.println("after swap");
        System.out.println("a->" + a + "\n" + "b->" + b);

        System.out.println(solution(array));

        System.out.println(11^8);
    }

    public static int solution(int[] a){
        int rv0 = flip0(a);
        int rv1 = flip1(a);
        return Math.min(rv1,rv0);
    }

    private static int flip1(int[] a){
        int rv = 0;
        int len = a.length;
        for(int i=0;i<len;i++){
            if(i%2==0){
                if(a[i]!=0){
                    rv++;
                }
            }else {
                if(a[i]!=1){
                    rv++;
                }
            }
        }
        return rv;
    }

    private static int flip0(int[] a){
        int rv = 0;
        int len = a.length;
        for(int i=0;i<len;i++){
            if(i%2==0){
                if(a[i]!=1){
                    rv++;
                }
            }else {
                if(a[i]!=0){
                    rv++;
                }
            }
        }
        return rv;
    }

//    public static int solution(int[] a){
//        int rv = -1;
//        List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList());
//        Collections.sort(list);
//        Iterator<Integer> itr = list.iterator();
//        while (itr.hasNext()){
//            Integer i = itr.next();
//            if (i<0 && itr.hasNext()){
//                continue;
//            } else if (!list.contains(i+1) && i > 0){
//                rv = i + 1;
//                break;
//            } else if (!itr.hasNext() && i<=0){
//                rv = 1;
//            }
//        }
//        return rv;
//    }
}
