package com.sandbox.okhttp;

import com.jayway.jsonpath.JsonPath;
import okhttp3.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by qianjie on 4/3/18.
 */
public class Sandbox {
    public static void main(String[] args) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        int maxIdleConnections = 5;
        long keepAliveDurationNs = 1000;
        builder.connectionPool(new ConnectionPool(maxIdleConnections,keepAliveDurationNs, TimeUnit.MILLISECONDS));
        OkHttpClient client = builder.build();
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(2222)
                .addPathSegments("/management/env").build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String json = response.body().string();
            Map<String,String> apolloConfig = JsonPath.read(json,"$.ApolloPropertySources:application");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.close();
        }

    }
}
