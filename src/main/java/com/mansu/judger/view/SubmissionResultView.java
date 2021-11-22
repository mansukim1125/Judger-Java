package com.mansu.judger.view;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mansu.judger.listener.WindowCloseListener;
import com.mansu.judger.model.dto.CompletedSubmissionSubmissionResultDTO;
import com.mansu.repo.ExecuteResultRepo;

public class SubmissionResultView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Vector<CompletedSubmissionSubmissionResultDTO> submissionResultsPerTestcase;

	/**
	 * Create the frame.
	 */
	public SubmissionResultView(Vector<CompletedSubmissionSubmissionResultDTO> submissionResultsPerTestcase) {
		setResizable(false);
		setTitle("채점 결과");
		this.submissionResultsPerTestcase = submissionResultsPerTestcase;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		
		setBounds(100, 100, 1012, 615);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(1280, 720));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 972, 556);
		contentPane.add(scrollPane);
		
		String[][] rows = generateTableRows();
		String[] header = generateTableHeader();
		
		table = new JTable(rows, header);
		
		scrollPane.setViewportView(table);
	}
	
	public String[][] generateTableRows() {
		
		String[][] rows = new String[submissionResultsPerTestcase.size()][4];
		for (int i = 0; i < rows.length; ++i) {
			rows[i] = new String[4];
//			POINT: 저장은 객체로
			int resultCode = submissionResultsPerTestcase.get(i).getResult();
			String resultString = ExecuteResultRepo.getResultStringById(resultCode);
			rows[i][0] = resultString;
			rows[i][1] = Integer.toString(submissionResultsPerTestcase.get(i).getCpuTime()) + " ms";
			rows[i][2] = Double.toString((double) (submissionResultsPerTestcase.get(i).getMemory() / 1048576)) + " MB";
			rows[i][3] = submissionResultsPerTestcase.get(i).isCorrect() ? "정답입니다!" : "틀렸습니다";
		}
		return rows;
	}

	public String[] generateTableHeader() {
		return new String[] { "채점 결과", "사용한 시간", "사용한 메모리", "정답 여부" };
	}
}
