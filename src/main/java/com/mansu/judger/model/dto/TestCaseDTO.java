package com.mansu.judger.model.dto;

public class TestCaseDTO {
    private int tc_num;
    private String input;
    private String output;

    public TestCaseDTO(int tc_num, String input, String output) {
        this.tc_num = tc_num;
        this.input = input;
        this.output = output;
    }

    public TestCaseDTO(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public int getTcNum() {
        return tc_num;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public void setTcNum(int tc_num) {
        this.tc_num = tc_num;
    }
}
