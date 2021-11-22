package com.mansu.judger.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import com.mansu.judger.model.dto.TestCaseDTO;
import com.mansu.judger.view.ExampleTestCaseInputView;
import com.mansu.judger.view.ExampleTestCaseOutputView;

public class ExampleTestCaseClickListener extends MouseAdapter {
	private TestCaseDTO testcase;
	
	public ExampleTestCaseClickListener(TestCaseDTO testcase) {
		// TODO Auto-generated constructor stub
		this.testcase = testcase;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();

		if (row == 0) return;
		
		switch (col) {
			case 0: 
				ExampleTestCaseInputView exampleTestCaseInputView = new ExampleTestCaseInputView(testcase.getInput());
				exampleTestCaseInputView.setVisible(true);
				break;
			case 1:
				ExampleTestCaseOutputView exampleTestCaseOutputView = new ExampleTestCaseOutputView(testcase.getOutput());
				exampleTestCaseOutputView.setVisible(true);
				break;
		}
	}
}
