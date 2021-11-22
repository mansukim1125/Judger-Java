package com.mansu.judger.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.mansu.judger.model.dto.ExecutableDTO;
import com.mansu.judger.model.dto.LanguageDTO;
import com.mansu.judger.util.docker.Client;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Compiler {
    private final LanguageDTO language;
    private final String code;
    private final Client client;
    private ExecutableDTO executable;

    public Compiler(LanguageDTO language, String code) {
        this.language = language;
        this.code = code;
        this.client = new Client();
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void execute() throws CompilationException {
        DockerClient client = this.client.getInstance();

        String containerId = this.client.createContainer();

        ByteArrayOutputStream codeArchive = this.generateCodeTarArchive();
        this.client.copyFileArchiveToContainer(containerId, new ByteArrayInputStream(codeArchive.toByteArray()), "/home/runner");

        StartContainerCmd cmd = client.startContainerCmd(containerId);
        cmd.exec();

        ByteArrayOutputStream outputStream = null;

        this.compile(containerId, this.getLanguage().getCompileCmds());
        try {
            outputStream = this.client.getFileArchiveFromContainer(containerId, this.getLanguage().getExecutableFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        this.compiledExecutable = outputStream;
        this.executable = new ExecutableDTO(outputStream);
    }

    private void compile(String containerId, String[] cmds) throws CompilationException {
//        TODO: �뤃�끃�뵠 占쎈퓠占쎌쑎�몴占� 疫꿸퀡以됵옙釉� 占쎈툡占쎌뒄揶쏉옙 占쎌뿳占쎌뱽繹먲옙?
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        DockerClient client = this.client.getInstance();

        String cmdId = client.execCreateCmd(containerId)
                .withAttachStdout(true)
                .withCmd(cmds)
                .exec()
                .getId();

        try {
            client.execStartCmd(cmdId)
                    .exec(new ByteArrayOutputStreamResultCallback(outputStream, errorStream))
                    .awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long exitCode = this.client.getExecuteStatusCode(cmdId);

        if (exitCode != 0) {
            throw new CompilationException(exitCode, errorStream.toString(StandardCharsets.UTF_8));
        }
    }

    private ByteArrayOutputStream generateCodeTarArchive() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchive = new TarArchiveOutputStream(byteArrayOutputStream);

        byte[] bytes = this.code.getBytes(StandardCharsets.UTF_8);
        TarArchiveEntry codeEntry = new TarArchiveEntry(this.language.getSourceFileName());

        codeEntry.setSize(bytes.length);

        try {
            tarArchive.putArchiveEntry(codeEntry);
            tarArchive.write(bytes);
            tarArchive.closeArchiveEntry();

            tarArchive.finish();
            tarArchive.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    public ExecutableDTO getExecutable() {
        return executable;
    }
}
