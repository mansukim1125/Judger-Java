package com.mansu.judger.view;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.mansu.judger.listener.SubmissionsListTableCellClickedListener;
import com.mansu.judger.listener.WindowCloseListener;
import com.mansu.judger.model.dto.CompletedSubmissionDTO;
import com.mansu.judger.renderer.SubmissionsListTableCellRenderer;
import com.mansu.judger.util.JudgmentLog;
import com.mansu.repo.LogFileRepo;

public class SubmissionsListView extends JFrame {

	private JPanel contentPane;
	private JTable submissionsTable;

	/**
	 * Create the frame.
	 */
	public SubmissionsListView() {
		setResizable(false);
		setTitle("제출 목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		
		setBounds(100, 100, 1034, 643);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(1280, 720));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 994, 584);
		contentPane.add(scrollPane);
		
		Object[][] rows = generateTableRows();
		String[] header = generateTableHeader(); 
		
		submissionsTable = new JTable(rows, header);
		submissionsTable.addMouseListener(new SubmissionsListTableCellClickedListener());
		
		for (int j = 0; j < header.length; ++j) {
//			POINT: 각 cell마다 Render방식 customize.
//			refer to: http://www.java2s.com/Tutorial/Java/0240__Swing/CreatingaCustomCellRendererinaJTableComponent.htm
			TableColumn col = submissionsTable.getColumnModel().getColumn(j);
		    col.setCellRenderer(new SubmissionsListTableCellRenderer());
		}
		
		scrollPane.setViewportView(submissionsTable);
	}

	public Object[][] generateTableRows() {
		Vector<CompletedSubmissionDTO> results = getSubmissionResults();
		Object[][] rows = new Object[results.size()][4];
		for (int i = 0; i < rows.length; ++i) {
			rows[i] = new Object[4];
//			POINT: 저장은 객체로
			rows[i][0] = results.get(i).getProblem();
			rows[i][1] = results.get(i).getResults();
			rows[i][2] = results.get(i).getCode();
			rows[i][3] = results.get(i).getLanguage();
		}
		return rows;
	}

	public String[] generateTableHeader() {
		return new String[] { "문제", "제출 상세", "코드", "언어" };
	}
	
	public Vector<CompletedSubmissionDTO> getSubmissionResults() {
		JudgmentLog log = new JudgmentLog(LogFileRepo.getInstance());
		return log.getInstance();
	}
}
