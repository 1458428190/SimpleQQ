/*
 * 注册QQ用户
 */
package com.qq.client.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.MyImageIcon;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientRegister {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JRadioButton radioButton,radioButton_1;
	private JTextField textField_3;
	public static void main(String[] args)
	{
		new QqClientRegister();
	}
	public QqClientRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 255, 255));
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 515;
		int h = 473;
		
		frame.setBounds(width/2-w/2, height/2-h/2, 515, 473);
		//frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D:");
		label.setBounds(114, 96, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u624B\u673A\u53F7:");
		label_1.setBounds(114, 133, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel lblNewLabel = new JLabel("\u5E74\u9F84:");
		lblNewLabel.setBounds(114, 245, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_2 = new JLabel("\u6027\u522B:");
		label_2.setBounds(114, 204, 72, 18);
		frame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setBounds(114, 276, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801:");
		label_3.setBounds(114, 307, 72, 18);
		frame.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(200, 93, 109, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 130, 109, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		radioButton = new JRadioButton("\u7537");
		radioButton.setBackground(new Color(0, 255, 255));
		radioButton.setBounds(196, 200, 52, 27);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setBackground(new Color(0, 255, 255));
		radioButton_1.setBounds(257, 200, 52, 27);
		frame.getContentPane().add(radioButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 273, 109, 24);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(200, 304, 109, 24);
		frame.getContentPane().add(passwordField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 242, 109, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblqq = new JLabel("\u6CE8\u518CQQ");
		lblqq.setFont(new Font("宋体", Font.BOLD, 21));
		lblqq.setForeground(new Color(255, 0, 0));
		lblqq.setBounds(216, 45, 72, 18);
		frame.getContentPane().add(lblqq);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUsername(textField.getText());
				user.setPhone(Integer.parseInt(textField_1.getText()));
				user.setAge(Integer.parseInt(textField_2.getText()));
				user.setQianming(textField_3.getText());
				String pwd1 = new String(passwordField.getPassword());
				String pwd2 = new String(passwordField_1.getPassword());
				if(!pwd1.equals(pwd2))
				{
					JOptionPane.showMessageDialog(null, "两次密码不一致");
				}
				else
				{
				user.setPassword(pwd1);
				//向服务器发送注册信息
				QqClientConServer qccs = new QqClientConServer();
				String res = qccs.SendUpdateInfo(user, MessageType.message_register);
				if(!res.equals("fail"))
				{
					JOptionPane.showMessageDialog(null, "注册成功, QQ号为:  "+res);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "注册失败, 注册信息有误!");
				}
				}
			}
		});
		button.setBounds(170, 352, 113, 27);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon image = new ImageIcon(QqClientRegister.class.getResource("/img/\u7433.jpg"));
		image.setImage(image.getImage().getScaledInstance(142, 96, Image.SCALE_DEFAULT));
		lblNewLabel_2.setIcon(image);
		lblNewLabel_2.setBounds(341, 57, 142, 96);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		JButton btnNewButton = new JButton("\u4E0A\u4F20\u5934\u50CF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(null);
				String path = jfc.getSelectedFile().getAbsolutePath();
				if(path!=null)
				{
				//System.out.println("path = " + path);
				MyImageIcon mii = new MyImageIcon(path, 142, 96);
				lblNewLabel_2.setIcon(mii);
//				frame.dispose();
//				frame.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(364, 166, 100, 24);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label_4 = new JLabel("\u4E2A\u6027\u7B7E\u540D:");
		label_4.setBounds(114, 173, 72, 18);
		frame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(200, 167, 109, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		frame.setVisible(true);
	}
}
