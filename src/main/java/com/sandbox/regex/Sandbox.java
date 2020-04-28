package com.sandbox.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sandbox {

	public static final Pattern pattern = Pattern.compile("<h1.*>");
	
	public static void main(String[] args){
		String url = "<h1>xxxx</h1>";
		Matcher matcher = pattern.matcher(url);
		System.out.println(matcher.matches());
		
	}
}
