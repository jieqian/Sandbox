package com.sandbox.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qianjie on 3/22/17.
 */
public class Sandbox {
	
	public void test(String name){
		String metricName = name;
	    String prefix = name.replace("abc", "aabbcc");
	    name = "empty";
//	    metricName = "aaabbbccc";
	    System.out.println(name);
	    System.out.println(prefix);
	    System.out.println(metricName);
	}
	
	public void test2(Map<String,String> map){
		Map<String,String> newMap = map;
		map = new HashMap<>();
		System.out.println(map);
		System.out.println(newMap);
	}
	
	public void test3(long value){
		System.out.println(-value);
	}
	
    public static void main(String[] args) throws InterruptedException {
//        TopValue topValue1 = new TopDoubleValue(1,30.0d,"three","1");
//        TopValue topValue2 = new TopDoubleValue(1,20.0d,"two","2");
//        TopValue topValue3 = new TopDoubleValue(1,10.0d,"one","3");
//
//        TopValue topValue4 = new TopDoubleValue(1,30.0d,"four","4");
//        TopValue topValue5 = new TopDoubleValue(1,20.0d,"five","5");
//        TopValue topValue6 = new TopDoubleValue(1,10.0d,"six","6");
//
//        TopValue topValue7 = new TopDoubleValue(1,31.0d,"one","7");
//        TopValue topValue8 = new TopDoubleValue(1,299.0d,"two1","8");
//        TopValue topValue9 = new TopDoubleValue(1,1.0d,"three","9");
//
//        List<TopValue<Double>> list = new TopValueList<>();
//        list.add(topValue1);
//        list.add(topValue2);
//        list.add(topValue3);
//
//        list.add(topValue4);
//        list.add(topValue5);
//        list.add(topValue6);
//
//        list.add(topValue7);
//        list.add(topValue8);
//        list.add(topValue9);
//
//        System.out.println(list.size());
//        list.forEach(doubleTopValue -> System.out.println(doubleTopValue));
//    	Config config = ConfigRepository.INSTANCE();
//    	while(!Thread.currentThread().isInterrupted()){
//    		System.out.println(config.getProperty("k1", "v99"));
//    		Thread.sleep(1000);
//    	}
    	
//    	Map<String,String> map = new HashMap<>();
//    	map.put("k1", "v1");
//    	Map<String,String> immutableMap = Collections.unmodifiableMap(new HashMap<String,String>());
//    	Map<String,String> rv = new HashMap<>();
//    	rv.putAll(map);
//    	rv.putAll(immutableMap);
//    	Map<String,String> otherRV = Collections.unmodifiableMap(rv);
//    	System.out.println(otherRV);
    	long l = Long.MAX_VALUE;
    	System.out.println(l);
    	System.out.println(l+1);
//    	Sandbox sb = new Sandbox();
//    	sb.test3(99);

    }
}
