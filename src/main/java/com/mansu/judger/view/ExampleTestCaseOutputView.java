package com.mansu.judger.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mansu.judger.listener.WindowCloseListener;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ExampleTestCaseOutputView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ExampleTestCaseOutputView(String string) {
		setTitle("출력 예시");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		
		setBounds(100, 100, 779, 464);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(1280, 720));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane exampleTestCaseOutputTextPane = new JTextPane();
		exampleTestCaseOutputTextPane.setText(string);
		
		scrollPane.setViewportView(exampleTestCaseOutputTextPane);
	}

}
