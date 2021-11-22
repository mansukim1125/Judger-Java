package com.mansu.judger.view;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mansu.judger.listener.ProblemListItemClickedListener;
import com.mansu.judger.listener.WindowCloseListener;
import com.mansu.judger.model.dao.ProblemDAO;
import com.mansu.judger.model.dto.ProblemDTO;
import com.mansu.judger.renderer.ProblemListItemRenderer;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ProblemsListView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ProblemsListView() {
		setResizable(false);
		setTitle("문제 목록");
//		TODO: 해보기: https://stackoverflow.com/questions/12210972/setdefaultcloseoperation-to-show-a-jframe-instead
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		WindowClose하면 해당 창 하나만 없애기.
		addWindowListener(new WindowCloseListener(this));
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(1366, 768));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Vector<ProblemDTO> problems = ProblemDAO.getProblems();
		contentPane.setLayout(null);
		
		JButton createProblemViewButton = new JButton("문제 생성");
		createProblemViewButton.setBounds(1232, 10, 122, 23);
		createProblemViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuperUserLoginView view = new SuperUserLoginView();
				view.setVisible(true);
			}
		});
		
		JButton SubmissionResultViewButton = new JButton("제출 보기");
		SubmissionResultViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubmissionsListView frame = new SubmissionsListView();
				frame.setVisible(true);
			}
		});
		SubmissionResultViewButton.setBounds(1084, 10, 122, 23);
		contentPane.add(SubmissionResultViewButton);
		contentPane.add(createProblemViewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 1342, 715);
		contentPane.add(scrollPane);
		
//		새 문제 리스트 생성
		JList<ProblemDTO> problemList = new JList<>(problems);

		problemList.setBounds(5, 5, 1356, 735);
		
		problemList.setFixedCellHeight(50);
		
		scrollPane.setViewportView(problemList);
		
//		리스트 렌더링 방법 결정.
		problemList.setCellRenderer(new ProblemListItemRenderer());

		problemList.addMouseListener(new ProblemListItemClickedListener());
		
		pack();
	}
}
