package com.sandbox.utils.pojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sandbox.gson.Gsons;
import com.sandbox.utils.TopValueList;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by qianjie on 3/21/17.
 */
public class JsonToObject {
    private static final Gson gson = Gsons.DEFAUTL.instance();

    public static Series getNonValueSeries(String json){
        Type listType = new TypeToken<Map<String,List<Map<String,List<Series>>>>>(){}.getType();
        Map<String,List<Map<String,List<Series>>>> map = gson.fromJson(json,listType);
        return map.get("results").get(0).get("series").get(0);
    }

    public static List<Series> getSeriesList(String json){
        Type listType = new TypeToken<Map<String,List<Map<String,List<Series>>>>>(){}.getType();
        Map<String,List<Map<String,List<Series>>>> map = gson.fromJson(json,listType);

        List<Series> seriesList = map.get("results").get(0).get("series");

        return seriesList;
    }

    public static List<TopValue<Double>> getTopDoubleValues(Series series){
        List<TopValue<Double>> topDoubleValues = new TopValueList<>();

        for(List<Object> valuePojo : series.getValues()){
            //convert scientific notation to long
            long time = new BigDecimal(valuePojo.get(0).toString()).longValue();

            double value = (Double)valuePojo.get(1);
            String tag1 = (String)valuePojo.get(2);
            String tag2 = (String)valuePojo.get(3);
            topDoubleValues.add(new TopDoubleValue(time,value,tag1,tag2));
        }

        return topDoubleValues;
    }
    
    public static void main(String[] args){
    	String json = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
    	Type mapType = new TypeToken<Map<String,String>>(){}.getType();
    	Map<String,String> map = gson.fromJson(json, mapType);
    	System.out.println(map);
    }
}
