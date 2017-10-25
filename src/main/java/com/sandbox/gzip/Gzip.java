package com.sandbox.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;

public class Gzip {

	public static byte[] compress(String data) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
		GZIPOutputStream gzip = new GZIPOutputStream(bos);
		gzip.write(data.getBytes());
		gzip.close();
		byte[] compressed = bos.toByteArray();
		bos.close();
		return compressed;
	}
	
	public static String decompress(byte[] compressed) throws IOException {
		/**
		 * https://gist.github.com/yfnick/227e0c12957a329ad138
		 * This doesn't work on very large strings (600k). I modified the decompress method a bit in that case.
		 * (Using apache commons io utils to get the bytes out)
		 * 
		 * */
//		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
//		GZIPInputStream gis = new GZIPInputStream(bis);
//		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while((line = br.readLine()) != null) {
//			sb.append(line);
//		}
//		br.close();
//		gis.close();
//		bis.close();
//		return sb.toString();
		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        byte[] bytes = IOUtils.toByteArray(gis);
        return new String(bytes, "UTF-8");
	}
	
	public static void main(String[] args) throws IOException{
		Gzip gzip = new Gzip();
		String str = "vash";
		byte[] compressed = gzip.compress(str);
		System.out.println(gzip.decompress(compressed));
		
	}
}
