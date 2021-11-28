package com.mansu.repo;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

public class DockerClientRepo {
    private static DockerClient client = null;

    public static DockerClient getInstance() {
    	if (client == null) {
    		client = DockerClientBuilder.getInstance(new DefaultDockerClientConfig.Builder().withDockerHost("tcp://localhost:2375/").build()).build();
    	}
        return client;
    }
}
