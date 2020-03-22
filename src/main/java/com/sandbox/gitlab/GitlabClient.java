package com.sandbox.gitlab;

import com.sandbox.utils.tuple.Tuple2;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

import java.util.ArrayList;
import java.util.List;

public enum  GitlabClient {
    V3(GitLabApi.ApiVersion.V3, "http://192.168.1.249","HsnYW8E_kusztj2Kzh8d");
    private GitLabApi gitLabApi;

    GitlabClient(GitLabApi.ApiVersion version, String url, String token){
        gitLabApi = new GitLabApi(version,url,token );
    }


    public List<Tuple2<String,String>> getProjects(){
        List<Tuple2<String,String>> projects = new ArrayList<>();
        try {
            List<Project> gitProjects = gitLabApi.getProjectApi().getProjects();

            gitProjects.forEach(project ->{
                String id = project.getId().toString();
                String name = project.getName();
                projects.add(new Tuple2<>(id,name));
            });
        } catch (GitLabApiException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
