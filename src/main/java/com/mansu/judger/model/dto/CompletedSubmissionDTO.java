package com.mansu.judger.model.dto;

import java.sql.Timestamp;
import java.util.Vector;

public class CompletedSubmissionDTO {
	/*
	 * [
	 * 	{
	 * 		"problem": {
	 * 			"title": "",
	 * 			"description": "",
	 * 			"cpu_time_limit": 123,
	 * 			"memory_limit": 213,
	 * 			""
	 * 		},
	 * 		"language": "C++",
	 * 		"code": "asdojpsiug9hoajk",
	 * 		"submitted_at": "2021110913562331213"
	 * 		"results": [
	 * 			{
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
	 * 		]
	 * 	}
	 * ]
	 * */
	
	private CompletedSubmissionProblemDTO problem;
	private CompletedSubmissionLanguageDTO language;
	private String code;
	private Timestamp submitted_at;
	private Vector<CompletedSubmissionSubmissionResultDTO> results;
	
	public CompletedSubmissionDTO(SubmissionRequestDTO submissionRequest, Vector<SubmissionResultDTO> results) {
		ProblemDTO problem = submissionRequest.getProblem();
		LanguageDTO language = submissionRequest.getLanguage();
		Vector<CompletedSubmissionSubmissionResultDTO> completedResults = new Vector<>();
		
		for (SubmissionResultDTO result : results) {
			completedResults.add(
				new CompletedSubmissionSubmissionResultDTO(
					result.getTcNum(),
					result.getCpuTime(),
					result.getRealTime(),
					result.getMemory(),
					result.getSignal(),
					result.getExitCode(),
					result.getResult(),
					result.isCorrect()
				)
			);
		}
		
		this.problem = new CompletedSubmissionProblemDTO(problem.getId(), problem.getTitle(), problem.getDescription(), problem.getMaxCpuTime(), problem.getMaxRealTime(), problem.getMaxMemory());
		this.language = new CompletedSubmissionLanguageDTO(language.getName());
		this.code = submissionRequest.getCode();
		this.submitted_at = new Timestamp(System.nanoTime());
		this.results = completedResults;
	}

	public CompletedSubmissionProblemDTO getProblem() {
		return problem;
	}

	public CompletedSubmissionLanguageDTO getLanguage() {
		return language;
	}

	public String getCode() {
		return code;
	}

	public Timestamp getSubmittedAt() {
		return submitted_at;
	}

	public Vector<CompletedSubmissionSubmissionResultDTO> getResults() {
		return results;
	}
}
