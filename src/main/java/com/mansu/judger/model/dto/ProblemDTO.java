package com.mansu.judger.model.dto;

import java.sql.Timestamp;
import java.util.Vector;

public class ProblemDTO {
    private int id;
    private String title;
    private String description;
    private int author_id;
    private int max_cpu_time;
    private int max_real_time;
    private int max_memory;
    private Vector<TestCaseDTO> testcases;
    private Timestamp created_at;

    public ProblemDTO(int id, String title, String description, int author_id, int max_cpu_time, int max_real_time, int max_memory, Vector<TestCaseDTO> testcases, Timestamp created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author_id = author_id;
        this.max_cpu_time = max_cpu_time;
        this.max_real_time = max_real_time;
        this.max_memory = max_memory;
        this.testcases = testcases;
        this.created_at = created_at;
    }

    public ProblemDTO(int id, String title, String description, int author_id, Timestamp created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author_id = author_id;
        this.created_at = created_at;
    }

    public ProblemDTO(String title, String description, int max_cpu_time, int max_real_time, int max_memory, Vector<TestCaseDTO> testcases) {
		this.title = title;
		this.description = description;
		this.max_cpu_time = max_cpu_time;
		this.max_real_time = max_real_time;
		this.max_memory = max_memory;
		this.testcases = testcases;
	}

	public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxCpuTime() {
        return max_cpu_time;
    }

    public int getMaxRealTime() {
        return max_real_time;
    }

    public int getMaxMemory() {
        return max_memory;
    }

    public Vector<TestCaseDTO> getTestcases() {
        return testcases;
    }
}
