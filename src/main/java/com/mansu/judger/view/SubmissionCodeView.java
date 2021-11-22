package com.mansu.judger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mansu.judger.listener.WindowCloseListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class SubmissionCodeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SubmissionCodeView(String code) {
		setTitle("제출한 코드");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		
		setSize(1280, 720);
		
		setBounds(100, 100, 1051, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JTextArea codeTextPane = new JTextArea();
		scrollPane.setViewportView(codeTextPane);
		
		codeTextPane.setText(code);
	}
}
