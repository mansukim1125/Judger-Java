package com.mansu.judger.model.dto;

public class Python3LanguageDTO extends LanguageDTO {
    public static final String[] compileCmds = {
            "python3.9",
            "-m",
            "py_compile",
            "/home/runner/main.py"
    };
    public static final String runCmd = "/usr/bin/python3.9";
    public static final String[] args = {
            "/home/runner/main.cpython-39.pyc"
    };
    public static final String sourceFileName = "main.py";
    public static final String executableFilePath = "/home/runner/__pycache__/main.cpython-39.pyc";
    public static final String executablePath = executableFilePath;
    public static final int memoryLimitCheckOnly = 0;

    public Python3LanguageDTO() {
        super("Python3", compileCmds, runCmd, args, sourceFileName, executablePath, executableFilePath, memoryLimitCheckOnly);
    }
}
