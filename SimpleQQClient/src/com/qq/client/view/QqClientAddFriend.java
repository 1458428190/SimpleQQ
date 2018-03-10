package com.qq.client.view;
/**
 * @author Àµ³Ì·æ
 *
 */
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class QqClientAddFriend {

	private JFrame frame;
	private JTextField txtqq;

	/**
	 * Create the application.
	 */
	public QqClientAddFriend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 650;
		int h = 300;
		frame.setResizable(false);
		frame.setBounds(width/2-w/2, height/2-h/2, w, h);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtqq = new JTextField();
		txtqq.setText("\u8F93\u5165QQ\u53F7\u7801/\u6635\u79F0/\u5173\u952E\u8BCD/\u624B\u673A\u53F7");
		txtqq.setBounds(0, 0, 395, 24);
		frame.getContentPane().add(txtqq);
		txtqq.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u627E");
		btnNewButton.setBounds(466, -1, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setBounds(77, 213, 113, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setBounds(414, 213, 113, 27);
		frame.getContentPane().add(button_1);
		
		JLabel lblNewLabel = new JLabel("\u6682\u65E0\u65F6\u95F4\u5B9E\u73B0....");
		lblNewLabel.setFont(new Font("ËÎÌå", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(35, 60, 544, 107);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("-------\u8D56\u7A0B\u950B");
		lblNewLabel_1.setFont(new Font("ËÎÌå", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(379, 158, 206, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		
		frame.setVisible(true);
	}
}
