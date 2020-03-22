package com.sandbox.gitlab;

import com.sandbox.utils.tuple.Tuple2;
import org.gitlab4j.api.GitLabApiException;

import java.util.List;

public class Sandbox {
    public static void main(String[] args) throws GitLabApiException {
        List<Tuple2<String,String>> projects = GitlabClient.V3.getProjects();
        projects.forEach(tuple2 -> {
            System.out.println(tuple2._1() + " || " + tuple2._2());
        });
    }
}
