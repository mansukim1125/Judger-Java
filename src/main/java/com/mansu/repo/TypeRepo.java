package com.mansu.repo;

import java.lang.reflect.Type;
import java.util.Vector;

import com.google.common.reflect.TypeToken;
import com.mansu.judger.model.dto.CompletedSubmissionDTO;
import com.mansu.judger.model.dto.TestCaseDTO;

public class TypeRepo {
	private static Type vectorOfTestCaseDTO = null;
	private static Type vectorOfCompletedSubmissionDTO = null;
	
	public static Type getVectorOfTestCaseDTO() {
		if (vectorOfTestCaseDTO == null) {
			vectorOfTestCaseDTO = new TypeToken<Vector<TestCaseDTO>>() {}.getType(); 
		}
		return vectorOfTestCaseDTO;
	}
	
	public static Type getVectorOfCompletedSubmissionDTO() {
		if (vectorOfCompletedSubmissionDTO == null) {
			vectorOfCompletedSubmissionDTO = new TypeToken<Vector<CompletedSubmissionDTO>>() {}.getType();
		}
		return vectorOfCompletedSubmissionDTO;
	}
}
