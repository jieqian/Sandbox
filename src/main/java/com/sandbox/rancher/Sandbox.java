package com.sandbox.rancher;

import com.cedarsoftware.util.io.JsonWriter;
import com.google.common.collect.Maps;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Sandbox {

    public static String projectId = "c-6qjtp:p-n5vcs";
    public static String namespace = "devops";
    public static String appName = "nginx";
    public static String image = "docker.io/bitnami/nginx:1.19.8-debian-10-r0";

    public static void main(String[] args) {

        System.out.println(getPodsStatus("nginx", "c-6qjtp:p-n5vcs","devops"));

//        System.out.println(removeDeployment(appName, projectId, namespace));

//        String json = new Deployment.Builder()
//                .name(appName)
//                .namespace("devops")
//                .image("docker.io/bitnami/nginx:1.19.8-debian-10-r0")
//                .scale(3)
//                .build();
//        System.out.println(createDeployment(json,projectId));

//        System.out.println(updateAppReplicaSet( 1, appName, projectId, namespace));
    }

    public static boolean createDeployment(String jsonRequestBody, String projectId){
        String path = "v3/projects/" + projectId + "/workload";
        RancherResponse response = RancherClient.DEFAULT.post(path, jsonRequestBody, Maps.newHashMap());
        if(response.success == false){
            log.error(response.message);
        }
        return response.success;
    }

    public static boolean updateAppReplicaSet(int scale, String appName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":"+ appName;
        String jsonRequestBody = new Deployment.Builder()
                .replicaSetSnippet(scale)
                .build();
        RancherResponse response = RancherClient.DEFAULT.put(path, jsonRequestBody, Maps.newHashMap());
        if(response.success == false){
            log.error(response.message);
        }
        return response.success;
    }

    public static boolean updateApplication(String image, int scale, String appName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":"+ appName;
        String jsonRequestBody = new Deployment.Builder()
                .appSnippet(image, appName, scale)
                .build();
        RancherResponse response = RancherClient.DEFAULT.put(path, jsonRequestBody, Maps.newHashMap());
        if(response.success == false){
            log.error(response.message);
        }
        return response.success;
    }

    public static boolean removeDeployment(String appName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":"+ appName;
        RancherResponse response = RancherClient.DEFAULT.delete(path, Maps.newHashMap());
        if(response.success == false){
            log.error(response.message);
        }
        return response.success;
    }

    public static String getPodsStatus(String appName, String projectId, String namespace){
        Map<String,String> queryParams = Maps.newHashMap();
        queryParams.put("limit","-1");
        queryParams.put("sort","name");
        String path = "v3/project/" + projectId + "/pods";
        String jsonRV = JsonWriter.formatJson(RancherClient.DEFAULT.get(path, queryParams));
        String jsonPath = "$.data[?(@.name=~/^" + appName + "-.*/i && @.namespaceId=='" + namespace + "')].['name','state']";
        return getJsonPathValue(jsonRV, jsonPath);
    }

    public static String getDeploymentStatus(String appName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":" + appName;
        String jsonRV = JsonWriter.formatJson(RancherClient.DEFAULT.get(path));
        String jsonPath = "$.state";
        return getJsonPathValue(jsonRV, jsonPath);
    }

    public static String getRunningPodCount(String appName, String projectId, String namespace){
        String path = "v3/project/" + projectId + "/workloads/deployment:" + namespace + ":"+ appName;
        String jsonRV = JsonWriter.formatJson(RancherClient.DEFAULT.get(path));

        String jsonPath = "$.deploymentStatus.availableReplicas";

        return getJsonPathValue(jsonRV, jsonPath);
    }

    private static String getJsonPathValue(String json, String jsonPath){
        return JsonPath.parse(json).read(jsonPath).toString();
    }
}
