package com.mansu.judger.listener;

import java.awt.event.*;

import javax.swing.JList;

import com.mansu.judger.model.dao.ProblemDAO;
import com.mansu.judger.model.dto.ProblemDTO;
import com.mansu.judger.view.ProblemDetailView;

public class ProblemListItemClickedListener extends MouseAdapter {


	@Override
	public void mouseClicked(MouseEvent e) {
		JList<ProblemDTO> list = (JList<ProblemDTO>) e.getSource();
		ProblemDTO selectedProblem = list.getSelectedValue();
		ProblemDTO problem = ProblemDAO.getProblemById(selectedProblem.getId());
		
		ProblemDetailView frame = new ProblemDetailView(problem);
		frame.setVisible(true);
	}

}
