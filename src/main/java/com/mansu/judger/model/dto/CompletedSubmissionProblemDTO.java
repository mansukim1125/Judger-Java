package com.mansu.judger.model.dto;

public class CompletedSubmissionProblemDTO {
	private int id;
    private String title;
    private String description;
    private int max_cpu_time;
    private int max_real_time;
    private int max_memory;
	
    public CompletedSubmissionProblemDTO(String title, String description, int max_cpu_time, int max_real_time, int max_memory) {
		this.title = title;
		this.description = description;
		this.max_cpu_time = max_cpu_time;
		this.max_real_time = max_real_time;
		this.max_memory = max_memory;
	}

	public CompletedSubmissionProblemDTO(int id, String title, String description, int max_cpu_time, int max_real_time, int max_memory) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.title = title;
		this.description = description;
		this.max_cpu_time = max_cpu_time;
		this.max_real_time = max_real_time;
		this.max_memory = max_memory;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxCpuTime() {
		return max_cpu_time;
	}

	public void setMaxCpuTime(int max_cpu_time) {
		this.max_cpu_time = max_cpu_time;
	}

	public int getMaxRealTime() {
		return max_real_time;
	}

	public void setMaxRealTime(int max_real_time) {
		this.max_real_time = max_real_time;
	}

	public int getMaxMemory() {
		return max_memory;
	}

	public void setMaxMemory(int max_memory) {
		this.max_memory = max_memory;
	}
}
