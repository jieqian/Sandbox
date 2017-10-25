package com.sandbox.gson;

import com.google.gson.Gson;

public class GsonHolder {
	private GsonHolder(){
	}

	private static final Gson gson = new Gson();
	
	public static Gson getGson(){
		return gson;
	}
}
