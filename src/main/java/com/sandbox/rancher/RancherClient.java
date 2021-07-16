package com.sandbox.rancher;


import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import javax.net.ssl.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public enum  RancherClient {
    DEFAULT("https",
            "172.25.17.80",
            8443,
            "Bearer token-g5clv:tmgp6bb7864hnhz958lqpb7nn84jlfljmc299tnftdmcvfbxx5lxp4");

    private OkHttpClient okHttpClient;
    private String scheme;
    private String host;
    private int port;
    private String bearerToken;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    RancherClient(String scheme, String host, int port, String bearerToken){
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.bearerToken = bearerToken;

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClientInstance = OKHttpClientSingleton.instance();
            OkHttpClient.Builder builder = okHttpClientInstance.newBuilder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);

            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.connectTimeout(30, TimeUnit.SECONDS);
            this.okHttpClient = builder.build();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String get(String path){
        return get(path, Maps.newHashMap());
    }

    public String get(String path, Map<String,String> queryParams){
        Request request = new Request.Builder()
                .url(buildHttpUrl(path, queryParams))
                .headers(buildHeaders())
                .get()
                .build();

        Response response = null;
        String responseValue = "";
        try{
            response = okHttpClient.newCall(request).execute();
            responseValue = response.body().string();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(response != null) response.close();
        }
        return responseValue;
    }

    public RancherResponse post(String path, String jsonRequestBody, Map<String,String> queryParams){
        Request request = new Request.Builder()
                .url(buildHttpUrl(path, queryParams))
                .headers(buildHeaders())
                .post(buildJSONRequestBody(jsonRequestBody))
                .build();

        Response response = null;
        RancherResponse rancherResponse = null;
        try{
            response = okHttpClient.newCall(request).execute();
            int code = response.code();
            if (code>=200 && code<300){
                rancherResponse = RancherResponse.builder().success(true).build();
            } else {
                rancherResponse = gson.fromJson(response.body().string(),new TypeToken<RancherResponse>(){}.getType());
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(response != null) response.close();
        }
        return Optional.ofNullable(rancherResponse).orElse(RancherResponse.builder().build());
    }

    public RancherResponse put(String path, String jsonRequestBody, Map<String,String> queryParams){
        Request request = new Request.Builder()
                .url(buildHttpUrl(path, queryParams))
                .headers(buildHeaders())
                .put(buildJSONRequestBody(jsonRequestBody))
                .build();

        Response response = null;
        RancherResponse rancherResponse = null;
        try{
            response = okHttpClient.newCall(request).execute();
            int code = response.code();
            if (code>=200 && code<300){
                rancherResponse = RancherResponse.builder().success(true).build();
            } else {
                rancherResponse = gson.fromJson(response.body().string(),new TypeToken<RancherResponse>(){}.getType());
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(response != null) response.close();
        }
        return Optional.ofNullable(rancherResponse).orElse(RancherResponse.builder().build());
    }

    public RancherResponse delete(String path, Map<String,String> queryParams){
        Request request = new Request.Builder()
                .url(buildHttpUrl(path, queryParams))
                .headers(buildHeaders())
                .delete()
                .build();

        Response response = null;
        RancherResponse rancherResponse = null;
        try{
            response = okHttpClient.newCall(request).execute();
            int code = response.code();
            if (code>=200 && code<300){
                rancherResponse = RancherResponse.builder().success(true).build();
            } else {
                rancherResponse = gson.fromJson(response.body().string(),new TypeToken<RancherResponse>(){}.getType());
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(response != null) response.close();
        }
        return Optional.ofNullable(rancherResponse).orElse(RancherResponse.builder().build());
    }

    private HttpUrl buildHttpUrl(String path, Map<String,String> queryParams){
        if(path.startsWith("/")){
            path = path.replaceFirst("/","");
        }

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme(scheme)
                .host(host)
                .port(port)
                .addPathSegments(path);
        queryParams.forEach((k,v)->{
            urlBuilder.addQueryParameter(k, v);
        });
        return urlBuilder.build();
    }

    private Headers buildHeaders(){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Authorization",bearerToken);
        headerMap.put("Accept","*/*");
        return Headers.of(headerMap);
    }

    private RequestBody buildJSONRequestBody(String json){
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);
        return body;
    }

    private static class OKHttpClientSingleton {
        private static OkHttpClient okHttpClient = new OkHttpClient();
        private OKHttpClientSingleton(){}
        private static OkHttpClient instance(){
            return okHttpClient;
        }
    }
}
