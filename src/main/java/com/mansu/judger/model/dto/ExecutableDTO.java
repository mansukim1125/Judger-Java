package com.mansu.judger.model.dto;

import java.io.ByteArrayOutputStream;

public class ExecutableDTO {
    private ByteArrayOutputStream compiledExecutable;

    public ExecutableDTO(ByteArrayOutputStream executable) {
        compiledExecutable = executable;
    }

    public ByteArrayOutputStream getExecutable() {
        return compiledExecutable;
    }
}
