/*
 * ���װ��QQ��¼����
 */
package com.qq.client.view;
/**
 * @author ���̷�
 *
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.qq.client.model.QqClientUser;
import com.qq.client.server.FriendListServer;
import com.qq.client.server.ManageQqClientFriendList;
import com.qq.common.User;

public class QqClientLogin{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private FriendListServer fls;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus���jdk6 update10�汾�Ժ�ĲŻ����
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//��ǰϵͳ���
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif���������
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//��ƽ̨��Java���
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows���
			//UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows���
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java���
			//UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");//�����죬
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QqClientLogin window = new QqClientLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QqClientLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(QqClientLogin.class.getResource("/img/qq.gif")));
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setResizable(false);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 450;
		int h = 300;
		
		frame.setBounds(width/2-w/2, height/2-h/2, w, h);
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQq = new JLabel("QQ\u8D26\u53F7:");
		lblQq.setForeground(new Color(0, 0, 0));
		lblQq.setFont(new Font("����", Font.PLAIN, 15));
		lblQq.setBounds(81, 88, 72, 18);
		frame.getContentPane().add(lblQq);
		
		JLabel lblQq_1 = new JLabel("QQ\u5BC6\u7801:");
		lblQq_1.setFont(new Font("����", Font.PLAIN, 15));
		lblQq_1.setBounds(81, 142, 72, 18);
		frame.getContentPane().add(lblQq_1);
		
		textField = new JTextField();
		textField.setBounds(154, 85, 115, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//ע���û�
		JLabel label = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		label.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QqClientRegister();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				}
		);
		label.setForeground(new Color(0, 0, 255));
		label.setBounds(314, 88, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u627E\u56DE\u5BC6\u7801");
		label_1.addMouseListener(new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QqClientPassword();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				}
			);
		label_1.setForeground(new Color(0, 0, 255));
		label_1.setBounds(314, 142, 72, 18);
		frame.getContentPane().add(label_1);
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 139, 115, 24);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User u = new User();
				u.setUseId(textField.getText().trim());
				u.setPassword(new String(passwordField.getPassword()));
				//System.out.println(u);
				QqClientUser qu = new QqClientUser();
				//����˵�¼��ť
				u = qu.checkUser(u);
				System.out.println("u==" +u);
				//���ظ�
				if(u!=null)
				{
					if(u.getUseId().equals("??????"))
					{
						JOptionPane.showMessageDialog(null, "���ѵ�¼���˺�,�������µ�¼");
					}
					else
					{
						//System.out.println("ysres");
						//������fls����֮ǰ,��Ȼ������Ҳ��������б����.
						QqClientFriendList qcfl = new QqClientFriendList(u);
						//�ӽ���������
						ManageQqClientFriendList.addQqClientFriendList(u.getUsername(), qcfl);
						System.out.println("wwwwwwwwwwwwwwwww" + ManageQqClientFriendList.hm2);
						FriendListServer fls = new FriendListServer();
						//�������к����б�
						fls.SendAllFriends(u.getUsername());
				
						//�������ߺ����б������
						
						fls.SendFriendListServer(u.getUsername());
						//�رյ���¼����
						frame.dispose();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "QQ������������");
				}
			}
		});
		button.setIcon(new ImageIcon(QqClientLogin.class.getResource("/img/denglu.gif")));
		button.setBounds(114, 196, 72, 27);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(QqClientLogin.class.getResource("/img/tou.gif")));
		lblNewLabel.setBounds(54, 13, 332, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setIcon(new ImageIcon(QqClientLogin.class.getResource("/img/quxiao.gif")));
		button_1.setBounds(230, 196, 72, 27);
		frame.getContentPane().add(button_1);
		frame.getContentPane().add(passwordField);
		frame.setVisible(true);
	}
}
