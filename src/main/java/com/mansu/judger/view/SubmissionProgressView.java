package com.mansu.judger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mansu.judger.model.dto.SubmissionRequestDTO;
import com.mansu.judger.service.JudgerService;
import com.mansu.judger.listener.JudgmentCompletedListener;
import com.mansu.judger.listener.SubmissionResultEventHandler;
import com.mansu.judger.listener.WindowCloseListener;

public class SubmissionProgressView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public SubmissionProgressView(SubmissionRequestDTO submissionRequest) {
		setResizable(false);
		setTitle("채점하는 중입니다...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		
		setSize(1280, 720);
		
		setBounds(100, 100, 865, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 825, 572);
		contentPane.add(scrollPane);
		
//		testcase 개수를 채점 결과 테이블 row 의 수로 함.
		int testCaseSize = submissionRequest.getProblem().getTestcases().size();

		String[][] rows = new String[testCaseSize][5];
		for (int i = 0; i < rows.length; ++i) {
			rows[i] = new String[5];
			rows[i][0] = "채점 중";
		}
		
//		테이블 생성.
		table = new JTable(rows, new String[] {"진행", "시간 (ms)", "실제 시간 (ms)", "메모리 (MB)", "정답"});
		
		scrollPane.setViewportView(table);
		
//		POINT: 채점 돌리기.
		judgment(submissionRequest, rows);
		
	}
	
	public void judgment(SubmissionRequestDTO submissionRequest, String[][] rows) {
		JudgerService judgerService = new JudgerService(submissionRequest.getProblem(), submissionRequest.getLanguage(), submissionRequest.getCode());
		judgerService.setResultEventHandler(new SubmissionResultEventHandler(table.getModel()));
		judgerService.setJudgmentCompletedEventHandler(new JudgmentCompletedListener(submissionRequest, SubmissionProgressView.this));
		Thread judgerServiceThread = new Thread(judgerService);
		judgerServiceThread.start();
	}
}
