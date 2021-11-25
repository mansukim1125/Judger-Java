package com.mansu.judger.listener;

import javax.swing.table.TableModel;

import com.mansu.judger.model.dto.SubmissionResultDTO;
import com.mansu.repo.ExecuteResultRepo;

public class SubmissionResultEventHandler {
	private TableModel resultTableModel;

	public SubmissionResultEventHandler(TableModel tableModel) {
		// TODO Auto-generated constructor stub
		this.resultTableModel = tableModel;
	}

	public void onResult(SubmissionResultDTO result) {
//		TODO: change below code to GUI handling.
		int currentTestCaseIndex = result.getTcNum() - 1;
		
		int resultField = result.getResult();
		String resultString = ExecuteResultRepo.getResultStringById(resultField);
		
		resultTableModel.setValueAt(resultString, currentTestCaseIndex, 0);
		resultTableModel.setValueAt(result.getCpuTime() + " ms", currentTestCaseIndex, 1);
		resultTableModel.setValueAt(result.getRealTime() + " ms", currentTestCaseIndex, 2);
		resultTableModel.setValueAt((double) (result.getMemory() / 1048576) + " MB", currentTestCaseIndex, 3);
		resultTableModel.setValueAt(result.isCorrect() ? "정답입니다!" : "틀렸습니다", currentTestCaseIndex, 4);
	}
}
