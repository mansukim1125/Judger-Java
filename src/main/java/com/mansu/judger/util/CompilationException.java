package com.mansu.judger.util;

public class CompilationException extends Exception {
    private long exitCode;
    private String message;
    
    public CompilationException() {
    }

    public CompilationException(long exitCode, String msg) {
    	this.message = msg;
        this.exitCode = exitCode;
    }

    public long getExitCode() {
        return exitCode;
    }

	public String getMessage() {
		return message;
	}
}
