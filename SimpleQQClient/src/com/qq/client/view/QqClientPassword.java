/*
 * 忘记密码时修改密码的界面
 */

package com.qq.client.view;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.common.MessageType;
import com.qq.common.User;
import java.awt.Color;

public class QqClientPassword {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private QqClientConServer qccs = new QqClientConServer();
	public QqClientPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(0, 255, 255));
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 450;
		int h = 300;
		
		frame.setBounds(width/2-w/2, height/2-h/2, w, h);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D:");
		label.setBounds(77, 60, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u624B\u673A\u53F7\u7801:");
		label_1.setBounds(77, 91, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(77, 133, 72, 18);
		frame.getContentPane().add(label_2);
		
		JLabel lblQq = new JLabel("QQ\u53F7:");
		lblQq.setBounds(77, 31, 72, 18);
		frame.getContentPane().add(lblQq);
		
		textField = new JTextField();
		textField.setBounds(163, 28, 110, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 57, 110, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(163, 88, 110, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\u65B0\u5BC6\u7801:");
		label_3.setBounds(77, 122, 72, 18);
		frame.getContentPane().add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 116, 110, 24);
		frame.getContentPane().add(passwordField);
		
		JLabel label_4 = new JLabel("\u786E\u8BA4\u5BC6\u7801:");
		label_4.setBounds(77, 153, 72, 18);
		frame.getContentPane().add(label_4);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(163, 150, 110, 24);
		frame.getContentPane().add(passwordField_1);
		
		JButton button = new JButton("\u786E\u8BA4\u6539\u5BC6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改密码
				User user = new User();
				user.setUseId(textField.getText().trim());
				user.setUsername(textField_1.getText().trim());
				user.setPhone(Integer.parseInt(textField_2.getText().trim()));
				
				String password = new String(passwordField.getPassword());
				String qrPassword = new String(passwordField_1.getPassword());
				if(password.equals(qrPassword))
				{
					user.setPassword(password);
					if(qccs.SendUpdateInfo(user, MessageType.message_gaimi).equals("true"))
						{
							JOptionPane.showMessageDialog(null, "修改密码成功");
						}
					else
					{
						JOptionPane.showMessageDialog(null, "填写信息有误");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "两次密码不一致");
				}
				
			}
		});
		button.setBounds(133, 198, 113, 27);
		frame.getContentPane().add(button);
		
		
		frame.setVisible(true);
	}

}
