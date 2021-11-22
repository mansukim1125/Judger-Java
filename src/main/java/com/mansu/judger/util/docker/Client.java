package com.mansu.judger.util.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectExecResponse;
import com.mansu.repo.DockerClientRepo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Client {
    private final DockerClient client;

    public Client() {
        this.client = DockerClientRepo.getInstance();
    }

    public DockerClient getInstance() {
        return client;
    }

    public String createContainer() {
        CreateContainerResponse container = this.client.createContainerCmd("mansukim1125/judger:1.1")
                .withTty(true)
                .withStdinOpen(true)
                .exec();
        return container.getId();
    }

    public void copyFileArchiveToContainer(String containerId, ByteArrayInputStream inputStream, String filepath) {
        this.client.copyArchiveToContainerCmd(containerId)
                .withTarInputStream(inputStream)
                .withRemotePath(filepath)
                .exec();
    }

    public ByteArrayOutputStream getFileArchiveFromContainer(String containerId, String filepath) throws IOException {
//        refer to: https://www.baeldung.com/java-inputstream-to-outputstream
        try (InputStream inputStream = this.client.copyArchiveFromContainerCmd(containerId, filepath).exec()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            inputStream.transferTo(outputStream);
            return outputStream;
        }
    }

    public long getExecuteStatusCode(String cmdId) {
        InspectExecResponse state = this.client.inspectExecCmd(cmdId).exec();
        return state.getExitCodeLong();
    }
}
