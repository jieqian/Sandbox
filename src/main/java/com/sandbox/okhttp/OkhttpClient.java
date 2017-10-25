package com.sandbox.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandbox.gson.Gsons;
import com.sandbox.utils.TopValueList;
import com.sandbox.utils.pojo.JsonToObject;
import com.sandbox.utils.pojo.Series;
import com.sandbox.utils.pojo.TopValue;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OkhttpClient {

	private static final Gson gson = Gsons.DEFAUTL.instance();

	public static void main(String[] args) throws IOException{
		OkHttpClient client = new OkHttpClient();
		String url = "";
		HttpUrl httpUrl = new HttpUrl.Builder()
		.scheme("http")
		.host("172.19.111.51")
		.port(7086)
		.addPathSegment("query")
		.addQueryParameter("pretty", "true")
		.addQueryParameter("db", "telegraf")
		.addQueryParameter("q", "SELECT top(\"value\", host, service, 3) FROM \"jvm_memory_heap_usage_value\" WHERE time > now() - 5m")
		.addQueryParameter("epoch","ms")
		.build();

		Request request = new Request.Builder()
	      .url(httpUrl)
	      .build();
		Response response = null;

		try{
		    response = client.newCall(request).execute();
			String json =  response.body().string();

			List<Series> sList = JsonToObject.getSeriesList(json);
			List<TopValue<Double>> topDoubleValues = new TopValueList<>();
			Series series = JsonToObject.getNonValueSeries(json);
			// accumulate and get top double values
			sList.forEach(s ->{
				List<TopValue<Double>> topValues = JsonToObject.getTopDoubleValues(s);
				topDoubleValues.addAll(topValues);
			});

			//transfer to ListList
			List<List<Object>> values = topDoubleValues.stream().map(doubleTopValue -> {
				List<Object> list = new ArrayList<>();
				list.add(doubleTopValue.getTime());
				list.add(doubleTopValue.getValue());
				list.add(doubleTopValue.getTag1());
				list.add(doubleTopValue.getTag2());
				return list;
			}).collect(Collectors.toList());

			//
			series.setValues(values);
			List<Series> seriesList = new ArrayList<>();
			seriesList.add(series);
			Map<String,List<Series>> seriesMap = new HashMap<>();
			seriesMap.put("series",seriesList);
			List<Map<String,List<Series>>> list = new ArrayList<>();
			list.add(seriesMap);
			Map<String,List<Map<String,List<Series>>>> map = new HashMap<>();
			map.put("results",list);

			System.out.println(gson.toJson(map));


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(response != null)
				response.close();
		}

	}
}
