package com.sandbox.regex;

import java.util.regex.Pattern;

public class Sandbox {

	public static final Pattern pattern = Pattern.compile("");
	
	public static void main(String[] args){
		String url = "/ppc-sdk/abc";
		url = url.replaceFirst("/ppc-sdk/", "/api/");
		System.out.println(url);
		
	}
}
