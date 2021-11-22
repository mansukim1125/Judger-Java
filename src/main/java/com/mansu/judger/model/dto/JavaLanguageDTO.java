package com.mansu.judger.model.dto;

public class JavaLanguageDTO extends LanguageDTO {
    public static final String[] compileCmds = {
            "javac",
            "/home/runner/Main.java",
            "-encoding",
            "UTF8"
    };
    public static final String runCmd = "/usr/bin/java";
    public static final String[] args = {
            "-cp",
            "/home/runner",
            "Main",
            "-XX:MaxRAM=134217728k",
            "-Djava.awt.headless=true"
    };
    public static final String sourceFileName = "Main.java";
    public static final String executableFilePath = "/home/runner/Main.class";
    public static final String executablePath = "/home/runner/Main";
    public static final int memoryLimitCheckOnly = 1;

    public JavaLanguageDTO() {
        super("Java", compileCmds, runCmd, args, sourceFileName, executablePath, executableFilePath, memoryLimitCheckOnly);
    }
}
