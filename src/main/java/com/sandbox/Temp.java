package com.sandbox;

public class Temp {
    public static void main(String[] args) {
        String json = "Bone-io ErestClient pool total stats: [leased: 0; pending: 0; available: 1; max: 1000]";
        int idx = json.indexOf("leased: ");
        Integer number = Integer.parseInt(json.substring(idx+8,idx+9));
        System.out.println(number);
    }
}
