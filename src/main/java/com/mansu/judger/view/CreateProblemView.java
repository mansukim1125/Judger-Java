package com.mansu.judger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mansu.judger.listener.WindowCloseListener;
import com.mansu.judger.model.dao.ProblemDAO;
import com.mansu.judger.model.dto.ProblemDTO;
import com.mansu.judger.model.dto.TestCaseDTO;
import com.mansu.repo.GsonRepo;
import com.mansu.repo.TypeRepo;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class CreateProblemView extends JFrame {

	private JPanel contentPane;
	private JLabel titleLabel;
	private JTextField titleField;
	private JLabel descriptionLabel;
	private JLabel cpuTimeLimitLabel;
	private JTextField cpuTimeLimitTextField;
	private JLabel realTimeLimitLabel;
	private JTextField realTimeLimitTextField;
	private JLabel memoryLimitLabel;
	private JTextField memoryLimitTextField;
	private JLabel testCaseTextArea;
	private JScrollPane testCaseScrollPane;
	private JEditorPane testcasesTextArea;

	/**
	 * Create the frame.
	 */
	public CreateProblemView() {
		setResizable(false);
		setTitle("문제 생성");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		setSize(1366, 768);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titleLabel = new JLabel("제목");
		titleLabel.setBounds(12, 10, 57, 15);
		contentPane.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(205, 7, 116, 21);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		descriptionLabel = new JLabel("설명");
		descriptionLabel.setBounds(12, 53, 57, 15);
		contentPane.add(descriptionLabel);
		
		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(206, 55, 1132, 243);
		contentPane.add(descriptionScrollPane);
		
		JEditorPane descriptionTextArea = new JEditorPane();
		descriptionScrollPane.setViewportView(descriptionTextArea);
		
		cpuTimeLimitLabel = new JLabel("CPU 시간 제한");
		cpuTimeLimitLabel.setBounds(12, 323, 92, 15);
		contentPane.add(cpuTimeLimitLabel);
		
		cpuTimeLimitTextField = new JTextField();
		cpuTimeLimitTextField.setBounds(205, 320, 116, 21);
		contentPane.add(cpuTimeLimitTextField);
		cpuTimeLimitTextField.setColumns(10);
		
		realTimeLimitLabel = new JLabel("실제 시간 제한");
		realTimeLimitLabel.setBounds(12, 378, 92, 15);
		contentPane.add(realTimeLimitLabel);
		
		realTimeLimitTextField = new JTextField();
		realTimeLimitTextField.setColumns(10);
		realTimeLimitTextField.setBounds(205, 375, 116, 21);
		contentPane.add(realTimeLimitTextField);
		
		memoryLimitLabel = new JLabel("메모리 크기 제한");
		memoryLimitLabel.setBounds(12, 431, 92, 15);
		contentPane.add(memoryLimitLabel);
		
		memoryLimitTextField = new JTextField();
		memoryLimitTextField.setColumns(10);
		memoryLimitTextField.setBounds(205, 428, 116, 21);
		contentPane.add(memoryLimitTextField);
		
		testCaseTextArea = new JLabel("테스트 데이터 입력");
		testCaseTextArea.setBounds(12, 482, 116, 15);
		contentPane.add(testCaseTextArea);
		
		testCaseScrollPane = new JScrollPane();
		testCaseScrollPane.setBounds(206, 476, 1132, 243);
		contentPane.add(testCaseScrollPane);
		
		testcasesTextArea = new JEditorPane();
		testCaseScrollPane.setViewportView(testcasesTextArea);
		
		JButton createProblemButton = new JButton("생성");
		createProblemButton.setBounds(1241, 22, 97, 23);
		
		
		createProblemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TODO: SubmissionRequestDTO를 SubmissionProgressView로 넘김.
				String title = titleField.getText();
				String description = descriptionTextArea.getText();
				int maxCpuTimeLimit = Integer.parseInt(cpuTimeLimitTextField.getText());
				int maxRealTimeLimit = Integer.parseInt(realTimeLimitTextField.getText());
				int memoryLimit = Integer.parseInt(memoryLimitTextField.getText());
				String testcasesStr = testcasesTextArea.getText();
				
				java.lang.reflect.Type listType = TypeRepo.getVectorOfTestCaseDTO();
				Vector<TestCaseDTO> testCases = GsonRepo.getInstance().fromJson(testcasesStr, listType);
				
				ProblemDTO newProblem = new ProblemDTO(title, description, maxCpuTimeLimit, maxRealTimeLimit, memoryLimit, testCases);
				
				ProblemDAO.createProblem(newProblem);
				
				setVisible(false);
				dispose();
				
			}
		});
		
		contentPane.add(createProblemButton);
	}
}
