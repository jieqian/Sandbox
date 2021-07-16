package com.sandbox.rancher;

import com.cedarsoftware.util.io.JsonWriter;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deployment {
    private static String deploymentTemplate = "{\n" +
            "    \"hostIPC\": false,\n" +
            "    \"hostNetwork\": false,\n" +
            "    \"hostPID\": false,\n" +
            "    \"paused\": false,\n" +
            "    \"type\": \"workload\",\n" +
            "    \"namespaceId\": \"devops\",\n" +
            "    \"scale\": 1,\n" +
            "    \"dnsPolicy\": \"ClusterFirst\",\n" +
            "    \"restartPolicy\": \"Always\",\n" +
            "    \"labels\": {},\n" +
            "    \"containers\": [\n" +
            "        {\n" +
            "            \"initContainer\": false,\n" +
            "            \"restartCount\": 0,\n" +
            "            \"stdin\": true,\n" +
            "            \"stdinOnce\": false,\n" +
            "            \"tty\": true,\n" +
            "            \"type\": \"container\",\n" +
            "            \"privileged\": false,\n" +
            "            \"allowPrivilegeEscalation\": false,\n" +
            "            \"readOnly\": false,\n" +
            "            \"runAsNonRoot\": false,\n" +
            "            \"namespaceId\": \"devops\",\n" +
            "            \"imagePullPolicy\": \"IfNotPresent\",\n" +
            "            \"environmentFrom\": [],\n" +
            "            \"resources\": {\n" +
            "                \"requests\": {},\n" +
            "                \"limits\": {}\n" +
            "            },\n" +
            "            \"capAdd\": [],\n" +
            "            \"capDrop\": [],\n" +
            "            \"image\": \"docker.io/bitnami/nginx:1.19.8-debian-10-r0\",\n" +
            "            \"ports\": [\n" +
            "                {\n" +
            "                    \"containerPort\": 80,\n" +
            "                    \"hostPort\": 0,\n" +
            "                    \"type\": \"containerPort\",\n" +
            "                    \"kind\": \"ClusterIP\",\n" +
            "                    \"protocol\": \"TCP\",\n" +
            "                    \"name\": \"nginx-instance\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"livenessProbe\": null,\n" +
            "            \"name\": \"nginx\",\n" +
            "            \"volumeMounts\": []\n" +
            "        }\n" +
            "    ],\n" +
            "    \"scheduling\": {\n" +
            "        \"node\": {}\n" +
            "    },\n" +
            "    \"deploymentConfig\": {\n" +
            "        \"minReadySeconds\": 0,\n" +
            "        \"type\": \"deploymentConfig\",\n" +
            "        \"revisionHistoryLimit\": 10,\n" +
            "        \"strategy\": \"RollingUpdate\",\n" +
            "        \"maxSurge\": 1,\n" +
            "        \"maxUnavailable\": 0\n" +
            "    },\n" +
            "    \"name\": \"nginx\",\n" +
            "    \"annotations\": {},\n" +
            "    \"volumes\": []\n" +
            "}";


    public static class Builder{
        private String json = "";

        public Builder() {
            this.json = deploymentTemplate;
        }

        public String build(){
            return this.json;
        }

        public Builder name(String name){
            this.json = JsonPath.parse(json).set(
                    "$.name",
                    name
            ).jsonString();
            this.json = JsonPath.parse(json).set(
                    "$.containers[0].name",
                    name
            ).jsonString();
            this.json = JsonPath.parse(json).set(
                    "$.containers[0].ports[0].name",
                    name
            ).jsonString();
            return this;
        }

        public Builder scale(int scale){
            this.json = JsonPath.parse(json).set(
                    "$.scale",
                    scale
            ).jsonString();
            return this;
        }

        public Builder namespace(String namespace){
            this.json = JsonPath.parse(json).set(
                    "$.namespaceId",
                    namespace
            ).jsonString();
            this.json = JsonPath.parse(json).set(
                    "$.containers[0].namespaceId",
                    namespace
            ).jsonString();
            return this;
        }

        public Builder image(String image){
            this.json = JsonPath.parse(json).set(
                    "$.containers[0].image",
                    image
            ).jsonString();
            return this;
        }

        public Builder appSnippet(String image, String appName, int scale){
            Map<String,String> map = new HashMap<>();
            map.put("name",appName);
            map.put("image", image);
            List<Map<String,String>> list = new ArrayList<>();
            list.add(map);
            this.json = JsonPath.parse("{}")
                    .put("$","containers", list)
                    .put("$", "scale", scale).jsonString();
            return this;
        }

        public Builder replicaSetSnippet(int scale){
            this.json = JsonPath.parse("{}")
                    .put("$", "scale", scale).jsonString();
            return this;
        }
    }


    public static void main(String[] args) {
        String json = new Deployment.Builder()
                .replicaSetSnippet(31)
                .build();
        System.out.println(JsonWriter.formatJson(json));
    }
}
