package com.mansu.judger.model.dto;

public class CompletedSubmissionSubmissionResultDTO {
		/* 			{				
	  	 * 				"tc_num": 1,
		 * 				...
		 * 				"cpu_time": 123,
		 * 				"real_time": "246",
		 * 				"memory": 8683520,
		 * 				"signal": 0,
		 * 				"exit_code": 0,
		 * 				"result": 0,
		 * 				"is_correct": true
		 * 			}
		 */
	
    private int tc_num;
    private int cpu_time;
    private int real_time;
    private long memory;
    private int signal;
    private int exit_code;
    private int result;
    private boolean is_correct;
	
    public CompletedSubmissionSubmissionResultDTO(int tc_num, int cpu_time, int real_time, long memory, int signal, int exit_code, int result, boolean is_correct) {
		this.tc_num = tc_num;
		this.cpu_time = cpu_time;
		this.real_time = real_time;
		this.memory = memory;
		this.signal = signal;
		this.exit_code = exit_code;
		this.result = result;
		this.is_correct = is_correct;
	}

	public int getTcNum() {
		return tc_num;
	}

	public void setTcNum(int tc_num) {
		this.tc_num = tc_num;
	}

	public int getCpuTime() {
		return cpu_time;
	}

	public void setCpuTime(int cpu_time) {
		this.cpu_time = cpu_time;
	}

	public int getRealTime() {
		return real_time;
	}

	public void setRealTime(int real_time) {
		this.real_time = real_time;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public int getExitCode() {
		return exit_code;
	}

	public void setExitCode(int exit_code) {
		this.exit_code = exit_code;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public boolean isCorrect() {
		return is_correct;
	}
}
