package com.mansu.judger.listener;

import com.mansu.judger.model.dao.ProblemDAO;
import com.mansu.judger.model.dto.ProblemDTO;
import java.awt.event.*;

public class CreateProblemSubmitListener implements ActionListener {
	private ProblemDTO problem;

	public CreateProblemSubmitListener(ProblemDTO problem) {
		this.problem = problem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ProblemDAO.createProblem(problem);
	}	
}
