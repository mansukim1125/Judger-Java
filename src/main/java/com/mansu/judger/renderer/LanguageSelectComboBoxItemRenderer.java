package com.mansu.judger.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.mansu.judger.model.dto.LanguageDTO;

public class LanguageSelectComboBoxItemRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		LanguageDTO language = (LanguageDTO) value; 
		super.getListCellRendererComponent(list, language.getName(), index, isSelected, cellHasFocus);
		
		// TODO Auto-generated method stub
		return this;
	}

}
