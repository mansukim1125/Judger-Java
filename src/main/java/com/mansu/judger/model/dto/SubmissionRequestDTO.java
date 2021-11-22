package com.mansu.judger.model.dto;

public class SubmissionRequestDTO {
//    TODO: ?�� ?��?��?���? client?��?��?�� ?��같이 ?��?��?��?�� ?��?��.
	private ProblemDTO problem;
    private LanguageDTO language;
    private String code;

    public SubmissionRequestDTO(ProblemDTO problem, LanguageDTO language, String code) {
        this.problem = problem;
        this.language = language;
        this.code = code;
    }

    public ProblemDTO getProblem() {
		return problem;
	}

	public void setProblem(ProblemDTO problem) {
		this.problem = problem;
	}
    
    public LanguageDTO getLanguage() {
        return language;
    }

	public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
