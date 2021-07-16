package com.sandbox.jsonpath;

import com.cedarsoftware.util.io.JsonWriter;
import com.google.common.collect.Maps;
import com.jayway.jsonpath.JsonPath;
import com.sandbox.rancher.RancherClient;
import com.sandbox.rancher.RancherResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by qianjie on 4/3/18.
 */
@Slf4j
public class Sandbox {

    public static void main(String[] args) {

//        System.out.println(getPodsStatus("nginx", "c-6qjtp:p-n5vcs","devops"));
        String createDeploytmentBody = "{\n" +
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
        System.out.println(createDeploytmentBody);
//        System.out.println(createDeployment(createDeploytmentBody,"nginx", "c-6qjtp:p-n5vcs","devops"));
    }

    public  static boolean createDeployment(String jsonRequestBody, String applicationName, String projectId, String namespace){
        String path = "v3/projects/" + projectId + "/workload";
        RancherResponse response = RancherClient.DEFAULT.post(path, jsonRequestBody, Maps.newHashMap());
        if(response.success == false){
            log.info(response.message);
        }
        return response.success;
    }

    public static boolean removeDeployment(String applicationName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":"+ applicationName;
        RancherResponse response = RancherClient.DEFAULT.delete(path, Maps.newHashMap());
        if(response.success == false){
            log.error(response.message);
        }
        return response.success;
    }

    public static String getPodsStatus(String applicationName, String projectId, String namespace){
        Map<String,String> queryParams = Maps.newHashMap();
        queryParams.put("limit","-1");
        queryParams.put("sort","name");
        String path = "v3/project/" + projectId + "/pods";
        String jsonRV = JsonWriter.formatJson(RancherClient.DEFAULT.get(path, queryParams));
        String jsonPath = "$.data[?(@.name=~/^" + applicationName + "-.*/i && @.namespaceId=='" + namespace + "')].['name','state']";
        return getJsonPathValue(jsonRV, jsonPath);
    }

    public static String getDeploymentStatus(String applicationName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":" + applicationName;
        String jsonRV = JsonWriter.formatJson(RancherClient.DEFAULT.get(path));
        String jsonPath = "$.state";
        return getJsonPathValue(jsonRV, jsonPath);
    }

    public static String getRunningPodCount(String applicationName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":"+ applicationName;
        String jsonRV = JsonWriter.formatJson(RancherClient.DEFAULT.get(path));

        String jsonPath = "$.deploymentStatus.availableReplicas";

        return getJsonPathValue(jsonRV, jsonPath);
    }

    private static String getJsonPathValue(String json, String jsonPath){
        return JsonPath.parse(json).read(jsonPath).toString();
    }
}
