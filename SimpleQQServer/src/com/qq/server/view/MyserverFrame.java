package com.qq.server.view;
/**
 * @author 赖程锋
 *
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.qq.server.model.MyQqServer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class MyserverFrame {

	private JFrame frame;
	private MyQqServer myQqServer;
	
	public static void main(String[] args) {
		new MyserverFrame();
	}
	/**
	 * Create the application.
	 */
	public MyserverFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setResizable(false);
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 520;
		int h = 500;
		
		frame.setBounds(width/2-w/2, height/2-h/2, w, h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u542F\u52A8\u670D\u52A1\u5668");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myQqServer = new MyQqServer();
			}
		});
		btnNewButton.setBounds(74, 267, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5173\u95ED\u670D\u52A1\u5668");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myQqServer.closeSocket();
			}
		});
		btnNewButton_1.setBounds(320, 267, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\u7B80\u964B\u7248\u7684QQ\u670D\u52A1\u5668");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 28));
		lblNewLabel.setBounds(127, 31, 306, 103);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u2014\u2014\u8D56\u7A0B\u950B");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setForeground(new Color(0, 0, 205));
		lblNewLabel_1.setBounds(336, 150, 152, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u2014\u20142017/12/10");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setBounds(302, 189, 141, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		frame.setVisible(true);
	}
}
