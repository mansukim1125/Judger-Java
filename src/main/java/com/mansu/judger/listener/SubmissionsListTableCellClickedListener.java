package com.mansu.judger.listener;

import java.awt.event.*;
import java.util.Vector;

import javax.swing.JTable;

import com.mansu.judger.model.dao.ProblemDAO;
import com.mansu.judger.model.dto.CompletedSubmissionProblemDTO;
import com.mansu.judger.model.dto.CompletedSubmissionSubmissionResultDTO;
import com.mansu.judger.model.dto.ProblemDTO;
import com.mansu.judger.view.ProblemDetailView;
import com.mansu.judger.view.SubmissionCodeView;
import com.mansu.judger.view.SubmissionResultView;

public class SubmissionsListTableCellClickedListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		switch (col) {
			case 0:
				CompletedSubmissionProblemDTO submissionProblem = (CompletedSubmissionProblemDTO) table.getModel().getValueAt(row, col);
				int problemId = submissionProblem.getId();
				ProblemDTO problem = ProblemDAO.getProblemById(problemId);
				if (problem == null) {
//					TODO: 404 Not Found View 보여주기.
					return;
				}
				ProblemDetailView problemDetailView = new ProblemDetailView(problem);
				problemDetailView.setVisible(true);
				break;
			case 1:
				Vector<CompletedSubmissionSubmissionResultDTO> submissionResultsPerTestcase = (Vector<CompletedSubmissionSubmissionResultDTO>) table.getModel().getValueAt(row, col);
				SubmissionResultView submissionResultView = new SubmissionResultView(submissionResultsPerTestcase);
				submissionResultView.setVisible(true);
				break;
			case 2:
				String code = (String) table.getModel().getValueAt(row, col);
				SubmissionCodeView codeView = new SubmissionCodeView(code);
				codeView.setVisible(true);
				break;
		}
	}
}
