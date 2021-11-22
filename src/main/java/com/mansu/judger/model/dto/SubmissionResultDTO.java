package com.mansu.judger.model.dto;

public class SubmissionResultDTO {
    private int tc_num;
    private int cpu_time;
    private int real_time;
    private long memory;
    private int signal;
    private int exit_code;
    private int error;
    private int result;
    private String output;
    private String error_msg;
    private boolean is_correct;

    public SubmissionResultDTO(int tc_num, int cpu_time, int real_time, long memory, int signal, int exit_code, int error, int result, String output, String error_msg, boolean is_correct) {
        this.tc_num = tc_num;
        this.cpu_time = cpu_time;
        this.real_time = real_time;
        this.memory = memory;
        this.signal = signal;
        this.exit_code = exit_code;
        this.error = error;
        this.result = result;
        this.output = output;
        this.error_msg = error_msg;
        this.is_correct = is_correct;
    }

    public int getTcNum() {
        return tc_num;
    }

    public int getCpuTime() {
        return cpu_time;
    }

    public int getRealTime() {
        return real_time;
    }

    public long getMemory() {
        return memory;
    }

    public int getSignal() {
        return signal;
    }

    public int getExitCode() {
        return exit_code;
    }

    public int getError() {
        return error;
    }

    public int getResult() {
        return result;
    }

    public String getOutput() {
        return output;
    }

    public String getErrorMsg() {
        return error_msg;
    }

    public boolean isCorrect() {
        return is_correct;
    }

    public void setTcNum(int tc_num) {
        this.tc_num = tc_num;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setIsCorrect(boolean b) {
        is_correct = b;
    }

    public void setErrorMsg(String errorMsg) {
        error_msg = errorMsg;
    }
}
