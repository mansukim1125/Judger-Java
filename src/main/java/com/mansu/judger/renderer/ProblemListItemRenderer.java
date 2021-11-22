package com.mansu.judger.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.*;

import com.mansu.judger.model.dto.ProblemDTO;

public class ProblemListItemRenderer extends JLabel implements ListCellRenderer<ProblemDTO> {

	@Override
	public Component getListCellRendererComponent(JList<? extends ProblemDTO> list, ProblemDTO problem, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		String title = problem.getTitle();
//		POINT: JLabel에 text set.
		setText(title);
		
		setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
//		refer to: https://stackoverflow.com/questions/14735085/clicking-a-jlabel-to-open-a-new-frame
//		addMouseListener(new ProblemListItemClickedListener());
		
		return this;
	}

}
