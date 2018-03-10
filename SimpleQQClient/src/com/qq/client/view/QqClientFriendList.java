package com.qq.client.view;
/**
 * @author 赖程锋
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.qq.client.model.QqClientConServer;
import com.qq.client.server.ManageClientConServerThread;
import com.qq.client.server.ManageQqChat;
import com.qq.client.server.ManageQqChatCaretaker;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientFriendList implements MouseListener{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel Friends[];    //好友名标签
	private User user;;
	private JPanel panel;
	private QqClientConServer qccs;
	/**
	 * Create the application.
	 */
	public static void main(String[] args) {
		new QqClientFriendList(null);
	}
	public QqClientFriendList(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(QqClientFriendList.class.getResource("/img/qq.gif")));
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setResizable(false);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 348;
		int h = 669;
		
		frame.setBounds(width/2-w/2, height/2-h/2, 348, 669);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(user.getUsername());  //用户名
		ImageIcon mii = new ImageIcon(QqClientFriendList.class.getResource("/img/\u61F5\u903C.jpg"));
		mii.setImage(mii.getImage().getScaledInstance(68,65, Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(mii);
		lblNewLabel.setBounds(14, 25, 68, 65);
		frame.getContentPane().add(lblNewLabel);
		
		//显示自己的名字
		JLabel lblNewLabel_1 = new JLabel(user.getUsername());
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(111, 13, 68, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("宋体", Font.BOLD, 15));
		textField.setForeground(Color.ORANGE);
		textField.setText(user.getQianming());   //个性签名
		textField.setBounds(99, 45, 136, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("LV"+user.getAge());
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setBounds(242, 16, 72, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setText("\u641C\u7D22\u597D\u53CB");
		textField_1.setToolTipText("");
		textField_1.setBounds(0, 103, 335, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 126, 335, 485);
		frame.getContentPane().add(scrollPane);
		
		//好友列表,先假设有50个好友(暂时的)
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panel);
/*		Friends = new JLabel[50];
		panel.setLayout(new GridLayout(50, 1, 4, 4));
		for(int i=0; i<50; i++)
		{
			Friends[i] = new JLabel((i+1)+"", new ImageIcon(QqClientRegister.class.getResource("/img/mm.jpg")), JLabel.LEFT);   //默认好友名是i,头像是mm;
			//默认只有自己在线,其他都是离线状态
			Friends[i].setEnabled(false);
			if(Friends[i].getText().equals(user.getUseId()))
			{
				Friends[i].setEnabled(true);
			}
			
			panel.add(Friends[i]);
			Friends[i].addMouseListener(this);
		}*/
		
		JButton button = new JButton("\u6DFB\u52A0\u597D\u53CB");
		button.setFont(new Font("宋体", Font.BOLD, 15));
		button.setBackground(Color.ORANGE);
		button.setForeground(new Color(0, 0, 205));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QqClientAddFriend();
			}
		});
		button.setBounds(0, 609, 113, 27);
		frame.getContentPane().add(button);
		
		JButton btnqq = new JButton("\u9000\u51FAQQ");
		btnqq.setFont(new Font("宋体", Font.BOLD, 15));
		btnqq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//发送下线通知
				Message ms = new Message();
				
				ms.setMesType(MessageType.message_xiaxian);
				ms.setUser(user);
				
				//发送给服务器
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(user.getUsername()).getS().getOutputStream());
					oos.writeObject(ms);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frame.dispose();
			}
		});
		btnqq.setForeground(new Color(0, 0, 205));
		btnqq.setBackground(Color.ORANGE);
		btnqq.setBounds(229, 609, 113, 27);
		frame.getContentPane().add(btnqq);
		
		frame.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		//双击了某个好友
		if(e.getClickCount()==2&&jl.isEnabled())
		{
			String friendName = jl.getText();
			QqClientChat qcc = QqClientChat.getInstance(this.user.getUsername(), friendName);
		}
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
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
	/**
	 * @param ms
	 * 更新在线的好友列表
	 */
	public void updateFriends(Message ms) {
		// TODO Auto-generated method stub
		
		//得到各个在线好友的用户id
		String onLineFriends[] = ms.getCon().split(" ");
		if(Friends!=null)
		{
		//找到在线好友
		for(int i=0; i<Friends.length; i++)
		{
			boolean ok = false;
			for(int j=0; j<onLineFriends.length; j++)
			{
				if(Friends[i].getText().trim().equals(onLineFriends[j]))
				{
					ok = true;
					break;
				}
			}
			if(ok)
			{
				//高亮在线好友
				Friends[i].setEnabled(true);
			}
		}
		}
	}
	
	/*
	 * 读取所有的服务器好友
	 */
	public void showFriends(String friends)
	{
		//System.out.println("hahahahaha");
		String friend[] = friends.split(" ");
		Friends = new JLabel[friend.length];
		panel.setLayout(new GridLayout(friend.length, 1, 4, 20));
		for(int i=0; i<friend.length; i++)
		{
			Friends[i] = new JLabel(friend[i], new ImageIcon(QqClientRegister.class.getResource("/img/mm.jpg")), JLabel.LEFT);   //默认好友名是i,头像是mm;
			//默认只有自己在线,其他都是离线状态
			Friends[i].setEnabled(false);
			if(Friends[i].getText().equals(user.getUsername()))
			{
				Friends[i].setEnabled(true);
			}
			
			panel.add(Friends[i]);
			Friends[i].addMouseListener(this);
		}
		frame.dispose();
		frame.setVisible(true);
	}
	/**
	 * 移除下线用户
	 */
	public void removeFriends(String con) {
		//把该好友设为灰暗的
		// TODO Auto-generated method stub
		for(int i=0; i<Friends.length; i++)
		{
			if(Friends[i].getText().equals(con))
			{
				Friends[i].setEnabled(false);
			}
		}
	}
}
