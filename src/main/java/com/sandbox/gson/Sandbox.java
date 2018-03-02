package com.sandbox.gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sandbox.utils.pojo.Pojo;
import com.sandbox.utils.pojo.Series;

public class Sandbox {
	public static void main(String[] args){
		Gson gson = GsonHolder.getGson();
		Object pojo = new Pojo();
		String str = gson.toJson(pojo);
		Type mapType = new TypeToken<Map<String,String>>(){}.getType();
		Map<String,String> map = gson.fromJson(str, mapType);
		System.out.println(map);
	}
}
