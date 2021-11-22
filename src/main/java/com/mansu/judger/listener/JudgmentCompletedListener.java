package com.mansu.judger.listener;

import java.util.Vector;

import com.mansu.judger.model.dto.CompletedSubmissionDTO;
import com.mansu.judger.model.dto.SubmissionRequestDTO;
import com.mansu.judger.model.dto.SubmissionResultDTO;
import com.mansu.judger.util.JudgmentLog;
import com.mansu.judger.view.SubmissionProgressView;
import com.mansu.repo.LogFileRepo;

public class JudgmentCompletedListener {
	private SubmissionRequestDTO submissionRequest;
	private SubmissionProgressView view;

	public JudgmentCompletedListener(SubmissionRequestDTO submissionRequest, SubmissionProgressView view) {
		this.submissionRequest = submissionRequest;
		this.view = view;
	}

	public void onResult(Vector<SubmissionResultDTO> judgeResult) {
		CompletedSubmissionDTO completedSubmission = new CompletedSubmissionDTO(submissionRequest, judgeResult);
		
		boolean isCorrect = false;
		for (SubmissionResultDTO result : judgeResult) {
			isCorrect = result.isCorrect();
		}
		
		view.setTitle("채점 완료! " + (isCorrect ? "정답입니다!" : "틀렸습니다!"));

//		POINT: JSON 내부 구조 발표자료에 포함하기.
		JudgmentLog log = new JudgmentLog(LogFileRepo.getInstance());
		log.add(completedSubmission);
		log.save();
	}
}
