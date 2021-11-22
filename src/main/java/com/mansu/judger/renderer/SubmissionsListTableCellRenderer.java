package com.mansu.judger.renderer;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.mansu.judger.model.dto.CompletedSubmissionProblemDTO;

public class SubmissionsListTableCellRenderer extends JLabel implements TableCellRenderer  {
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//		refer to: http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingaCustomCellRendererinaJTableComponent.htm
		String text = "";
//		POINT: 출력은 String으로.
		switch (column) {
			case 0:
				CompletedSubmissionProblemDTO problem = (CompletedSubmissionProblemDTO) value;
				text = problem.getTitle();
				break;
			case 1:
				text = "제출 상세 보기";
				break;
			case 2:
				text = "코드 보기";
				break;
		}
		setText(text);

		return this;
	}
}
