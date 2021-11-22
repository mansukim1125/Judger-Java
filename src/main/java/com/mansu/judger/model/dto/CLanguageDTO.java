package com.mansu.judger.model.dto;

public class CLanguageDTO extends LanguageDTO {
    public static final String[] compileCmds = {
            "gcc",
            "/home/runner/main.c",
            "-o",
            "/home/runner/main"
    };
    public static final String runCmd = "/home/runner/main";
    public static final String[] args = {};
    public static final String sourceFileName = "main.c";
    public static final String executablePath = "/home/runner/main";
    public static final String executableFilePath = executablePath;
    public static final int memoryLimitCheckOnly = 0;

    public CLanguageDTO() {
        super("C", compileCmds, runCmd, args, sourceFileName, executablePath, executableFilePath, memoryLimitCheckOnly);
    }
}
