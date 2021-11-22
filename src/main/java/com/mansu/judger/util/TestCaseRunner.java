package com.mansu.judger.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.google.gson.Gson;
import com.mansu.judger.listener.SubmissionResultEventHandler;
import com.mansu.judger.model.dto.*;
import com.mansu.judger.util.docker.Client;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestCaseRunner implements Runnable {
    private final ExecutableDTO executable;
    private final ProblemDTO problem;
    private final LanguageDTO language;
    private final TestCaseDTO testcase;

    private SubmissionResultDTO result;

    private final Client client;

    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

	private SubmissionResultEventHandler handler;

    interface Callback {
        void getResult(SubmissionResultDTO result);
    }

    public TestCaseRunner(ExecutableDTO executable, ProblemDTO problem, TestCaseDTO testcase, LanguageDTO language) {
        this.executable = executable;
        this.problem = problem;
        this.language = language;
        this.testcase = testcase;

        this.client = new Client();
    }

    public void setHandler(SubmissionResultEventHandler handler) {
        this.handler = handler;
    }

    public ByteArrayOutputStream generateTestCaseArchive() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TarArchiveOutputStream tarArchive = new TarArchiveOutputStream(byteArrayOutputStream);

        byte[] inputBytes = testcase.getInput().getBytes(StandardCharsets.UTF_8);

        TarArchiveEntry inputEntry = new TarArchiveEntry("inputs/" + testcase.getTcNum() + ".in");
        inputEntry.setSize(inputBytes.length);

        TarArchiveEntry outputEntry = new TarArchiveEntry("outputs/");

        tarArchive.putArchiveEntry(inputEntry);
        tarArchive.write(inputBytes);
        tarArchive.closeArchiveEntry();

        tarArchive.putArchiveEntry(outputEntry);
        tarArchive.closeArchiveEntry();

        tarArchive.finish();
        tarArchive.close();

        return byteArrayOutputStream;
    }

    @Override
    public void run() {
        DockerClient client = this.client.getInstance();
        String containerId = this.client.createContainer();

        ByteArrayOutputStream testcaseArchive = null;
        try {
            testcaseArchive = generateTestCaseArchive();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.client.copyFileArchiveToContainer(containerId, new ByteArrayInputStream(this.executable.getExecutable().toByteArray()), "/home/runner/");
        this.client.copyFileArchiveToContainer(containerId, new ByteArrayInputStream(testcaseArchive.toByteArray()), "/root/");

        StartContainerCmd cmd = client.startContainerCmd(containerId);
        cmd.exec();

        String cmds = generateExecuteCmd(this.problem.getMaxCpuTime(), this.problem.getMaxRealTime(), this.problem.getMaxMemory(), 33554432, 10000, 200, this.language.getRunCmd(), "/root/inputs/" + testcase.getTcNum() + ".in", "/root/outputs/" + testcase.getTcNum() + ".out", "/root/outputs/" + testcase.getTcNum() + ".error", this.language.getArgs(), new String[]{}, "/root/outputs/" + testcase.getTcNum() + ".log", 1234, 0, this.language.getMemoryLimitCheckOnly());
        try {
            this.execute(containerId, cmds.split(" "));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String output = this.outputStream.toString();

        Gson gson = new Gson();

        SubmissionResultDTO result = gson.fromJson(output, SubmissionResultDTO.class);
        result.setTcNum(testcase.getTcNum());
        if (result.getOutput().equals(this.problem.getTestcases().get(testcase.getTcNum() - 1).getOutput())) {
            result.setIsCorrect(true);
        }

        this.result = result;
        if (handler != null) {
            handler.onResult(result);
        }
    }

    private String generateExecuteCmd(int maxCpuTime, int maxRealTime, long maxMemory, long maxStack, int maxOutputSize, int maxProcessNumber, String exePath, String inputPath, String outputPath, String errorPath, String[] args, String[] envs, String logPath, int uid, int gid, int memoryLimitCheckOnly) {
        StringBuffer argsString = new StringBuffer("[]");
        StringBuffer envsString = new StringBuffer("[]");
        if (args.length > 0) {
            argsString = new StringBuffer("");
            for (String arg : args) {
                argsString.append(arg + ",");
            }
        }
        if (envs.length > 0) {
            envsString = new StringBuffer("");
            for (String env : envs) {
                envsString.append(env + ",");
            }
        }

        String cmd = "java -jar app.jar %d %d %d %d %d %d %s %s %s %s %s %s %s %d %d %d";

        return String.format(cmd, maxCpuTime, maxRealTime, maxMemory, maxStack, maxOutputSize, maxProcessNumber, exePath, inputPath, outputPath, errorPath, argsString, envsString, logPath, uid, gid, memoryLimitCheckOnly);

//        String cmd = "java -jar app.jar %d %d %d %d %d %d %s %s %s %s %s %s %s %d %d %d".formatted(maxCpuTime, maxRealTime, maxMemory, maxStack, maxOutputSize, maxProcessNumber, exePath, inputPath, outputPath, errorPath, argsString, envsString, logPath, uid, gid, memoryLimitCheckOnly);

//        return cmd;
    }

    public void execute(String containerId, String[] cmds) throws InterruptedException {
        this.outputStream = new ByteArrayOutputStream();
        this.errorStream = new ByteArrayOutputStream();
        DockerClient client = this.client.getInstance();

        String cmdId = client.execCreateCmd(containerId)
                .withAttachStdout(true)
                .withCmd(cmds)
                .exec()
                .getId();

        client.execStartCmd(cmdId)
                .exec(new ByteArrayOutputStreamResultCallback(outputStream, errorStream))
                .awaitCompletion();
    }

    public SubmissionResultDTO getResult() {
        return result;
    }
}
