package com.mansu.judger.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mansu.judger.listener.ExampleTestCaseClickListener;
import com.mansu.judger.listener.WindowCloseListener;
import com.mansu.judger.model.dto.CLanguageDTO;
import com.mansu.judger.model.dto.CPPLanguageDTO;
import com.mansu.judger.model.dto.JavaLanguageDTO;
import com.mansu.judger.model.dto.LanguageDTO;
import com.mansu.judger.model.dto.ProblemDTO;
import com.mansu.judger.model.dto.Python3LanguageDTO;
import com.mansu.judger.model.dto.SubmissionRequestDTO;
import com.mansu.judger.model.dto.TestCaseDTO;
import com.mansu.judger.renderer.LanguageSelectComboBoxItemRenderer;

import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProblemDetailView extends JFrame {

	private JPanel contentPane;
	private JTable InputOutputExampleTable;
	private JTable resourceLimitTable;

	/**
	 * Create the frame.
	 */
	public ProblemDetailView(ProblemDTO problem) {
		setTitle("문제를 해결하십시오: " + problem.getTitle());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addWindowListener(new WindowCloseListener(this));
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(1366, 768));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel problemTitle = new JLabel(problem.getTitle());
		problemTitle.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		problemTitle.setBounds(12, 10, 1342, 27);
		contentPane.add(problemTitle);
		
		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(12, 47, 1342, 233);
		contentPane.add(descriptionScrollPane);
		
		JTextArea descriptionTextArea = new JTextArea(problem.getDescription());
		descriptionTextArea.setEnabled(false);
		descriptionScrollPane.setViewportView(descriptionTextArea);
		
		JLabel resourceLimitLabel = new JLabel("리소스 제한");
		resourceLimitLabel.setBounds(12, 290, 134, 15);
		contentPane.add(resourceLimitLabel);
		
		TestCaseDTO firstTestCase = problem.getTestcases().get(0);
		
		String[][] rows = new String[2][2];
		rows[0] = new String[2];
		rows[0][0] = "입력";
		rows[0][1] = "출력";
		rows[1][0] = "보기";
		rows[1][1] = "보기";
		
		InputOutputExampleTable = new JTable(rows, new String[] { "입력", "출력" });
		InputOutputExampleTable.setEnabled(true);

		InputOutputExampleTable.setBounds(352, 315, 177, 32);
		
		InputOutputExampleTable.addMouseListener(new ExampleTestCaseClickListener(firstTestCase));
		
		contentPane.add(InputOutputExampleTable);
		
		JLabel InputOutputExampleLabel = new JLabel("입출력 예시");
		InputOutputExampleLabel.setBounds(352, 290, 134, 15);
		contentPane.add(InputOutputExampleLabel);
		
		JScrollPane codeEditorScrollPane = new JScrollPane();
		codeEditorScrollPane.setBounds(12, 357, 1342, 401);
		contentPane.add(codeEditorScrollPane);
		
		JEditorPane codeEditor = new JEditorPane();
		codeEditor.setFont(new Font("Cascadia Code PL", Font.PLAIN, 12));
		codeEditorScrollPane.setViewportView(codeEditor);
		
		JComboBox<LanguageDTO> languageSelectComboBox = new JComboBox<>();
		languageSelectComboBox.setModel(new DefaultComboBoxModel<>(new LanguageDTO[] {new CLanguageDTO(),new CPPLanguageDTO() , new JavaLanguageDTO(), new Python3LanguageDTO()}));
		
		languageSelectComboBox.setRenderer(new LanguageSelectComboBoxItemRenderer());
		
		languageSelectComboBox.setBounds(1138, 318, 94, 23);
		
		contentPane.add(languageSelectComboBox);
		
		JButton submitButton = new JButton("제출");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TODO: SubmissionRequestDTO를 SubmissionProgressView로 넘김.
//				
				LanguageDTO selectedLanguage = (LanguageDTO) languageSelectComboBox.getSelectedItem();
				String code = codeEditor.getText();

				SubmissionRequestDTO submissionRequest = new SubmissionRequestDTO(problem, selectedLanguage, code);
				
				SubmissionProgressView frame = new SubmissionProgressView(submissionRequest);
				
				frame.setVisible(true);
			}
		});
		submitButton.setBounds(1260, 318, 94, 23);
		contentPane.add(submitButton);
		
		resourceLimitTable = new JTable();
		resourceLimitTable.setEnabled(false);
		resourceLimitTable.setModel(new DefaultTableModel(
			new String[][] {
				{"시간 제한", "실제 시간 제한", "메모리 제한"},
				{ problem.getMaxCpuTime() + " ms", problem.getMaxMemory() / 1048576 + " MB", problem.getMaxRealTime() + " ms"},
			},
			new String[] {
				"timeLimit", "realTimeLimit", "memoryLimit"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Long.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		resourceLimitTable.setBounds(12, 315, 298, 32);
		contentPane.add(resourceLimitTable);
		
		pack();
	}
}
