package com.mansu.judger.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class WindowCloseListener extends WindowAdapter {
	private JFrame frame;
	
	public WindowCloseListener(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		frame.setVisible(false);
		frame.dispose();
	}
}
