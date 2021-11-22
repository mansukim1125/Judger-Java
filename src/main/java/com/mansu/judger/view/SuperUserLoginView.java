package com.mansu.judger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mansu.judger.listener.WindowCloseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SuperUserLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public SuperUserLoginView() {
		setResizable(false);
		setTitle("문제를 생성하려면 인증이 필요합니다");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowCloseListener(this));
		setBounds(100, 100, 548, 318);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(640, 480));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginViewTitle = new JLabel("관리자 인증");
		loginViewTitle.setBounds(12, 10, 139, 15);
		contentPane.add(loginViewTitle);
		
		JLabel usernameLabel = new JLabel("계정명");
		usernameLabel.setBounds(12, 64, 57, 15);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("패스워드");
		passwordLabel.setBounds(12, 118, 57, 15);
		contentPane.add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(85, 61, 116, 21);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(85, 115, 116, 21);
		contentPane.add(passwordField);
		
		JLabel loginStatus = new JLabel("인증 바랍니다");
		loginStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		loginStatus.setBounds(369, 10, 151, 15);
		contentPane.add(loginStatus);
		
		JButton loginButton = new JButton("인증");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TODO: 계정명, 패스워드 맞는지 확인 -> 문제 생성 페이지로 이동.
				String username = usernameTextField.getText();
				char[] passwordArray = passwordField.getPassword();
				String password = new String(passwordArray);
				
				if (username.equals("root") && password.equals("05300530")) {
					CreateProblemView view = new CreateProblemView();
					view.setVisible(true);
				} else {
					loginStatus.setText("다시 시도해주세요");
				}
			}
		});
		
		loginButton.setBounds(423, 246, 97, 23);
		contentPane.add(loginButton);
	}
}
