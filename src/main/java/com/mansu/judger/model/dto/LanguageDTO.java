package com.mansu.judger.model.dto;

public class LanguageDTO {
    private final String executableFilePath;
    private String name;
    private final String[] compileCmds;
    private final String runCmd;
    private final String[] args;
    private final String sourceFileName;
    private final String executablePath;
    private final int memoryLimitCheckOnly;

    public LanguageDTO(String name, String[] compileCmds, String runCmd, String[] args, String sourceFileName, String executablePath, String executableFilePath, int memoryLimitCheckOnly) {
        this.name = name;
        this.compileCmds = compileCmds;
        this.runCmd = runCmd;
        this.args = args;
        this.sourceFileName = sourceFileName;
        this.executablePath = executablePath;
        this.executableFilePath = executableFilePath;
        this.memoryLimitCheckOnly = memoryLimitCheckOnly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCompileCmds() {
        return compileCmds;
    }

    public String getRunCmd() {
        return runCmd;
    }

    public String[] getArgs() {
        return args;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public String getExecutablePath() {
        return executablePath;
    }

    public String getExecutableFilePath() {
        return executableFilePath;
    }

    public int getMemoryLimitCheckOnly() {
        return memoryLimitCheckOnly;
    }
}
