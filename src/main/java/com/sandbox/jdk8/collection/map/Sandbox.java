package com.sandbox.jdk8.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Sandbox {
	
	public String map2csv(Map<String,String> map){
		String rv =  "{" + map.entrySet().stream().map(entry ->{
            String k = entry.getKey();
            String v = entry.getValue();
            String str = k+""+":"+""+v;
            return str;}
        ).collect(Collectors.joining(",")) + "}";
		
		return rv;
	}
	
	public static void main(String[] args){
		Sandbox sandbox  = new Sandbox();
		Map<String,String> strMap = new HashMap<>();
		strMap.put("k1", "v1");
		strMap.put("k2", "v2");
		strMap.put("k3", "v3");
		System.out.println(sandbox.map2csv(strMap));
	}
}
