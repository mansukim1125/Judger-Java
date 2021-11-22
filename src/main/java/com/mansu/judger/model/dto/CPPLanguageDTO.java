package com.mansu.judger.model.dto;

public class CPPLanguageDTO extends LanguageDTO {
    public static final String[] compileCmds = {
            "g++",
            "/home/runner/main.cpp",
            "-o",
            "/home/runner/main"
    };
    public static final String runCmd = "/home/runner/main";
    public static final String[] args = {};
    public static final String sourceFileName = "main.cpp";
    public static final String executablePath = "/home/runner/main";
    public static final String executableFilePath = executablePath;
    public static final int memoryLimitCheckOnly = 0;

    public CPPLanguageDTO() {
        super("C++", compileCmds, runCmd, args, sourceFileName, executablePath, executableFilePath, memoryLimitCheckOnly);
    }
}
