package com.qq.client.view;
import java.awt.Image;
/**
 * @author 赖程锋
 *
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.qq.client.server.ManageClientConServerThread;
import com.qq.client.server.ManageQqChat;
import com.qq.client.server.ManageQqChatCaretaker;
import com.qq.client.tools.MyImageIcon;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class QqClientChat{

	private JFrame frmNi;
	private String owner;
	private String friendName;
	private JTextArea textArea_1;
	private JTextArea textArea;
	private Caretaker c;
	/**
	 * Create the application.
	 */
	private QqClientChat()
	{
		
	}
	
	private QqClientChat(String owner, String friendName)
	{
		this.owner = owner;
		this.friendName = friendName;
		initialize();
	}
	
	public static QqClientChat getInstance(String owner, String friendName)
	{
		QqClientChat qcc;
		if(ManageQqChat.getQqClientChat(owner+" "+friendName)!=null)
			return ManageQqChat.getQqClientChat(owner+" "+friendName);
		else
		{
			qcc = new QqClientChat(owner, friendName);
			//把聊天界面加入到管理类中,有待改进,  当对方的聊天窗口未打开时有问题(空)
			ManageQqChat.addQqClientChat(owner+" "+friendName, qcc);
			//新建一个对应该聊天窗口的备忘录负责类,并加进管理类中
			Caretaker c = new Caretaker();
			String ownerAndFriend = owner+" "+friendName;
			ManageQqChatCaretaker.addCaretaker(ownerAndFriend, c);
			return qcc;
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNi = new JFrame();
		frmNi.setTitle(owner + " 正在和 " +friendName+" 聊天");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = 650;
		int h = 600;
		frmNi.setResizable(false);
		frmNi.setBounds(width/2-w/2, height/2-h/2, 653, 508);
		//frmNi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNi.getContentPane().setLayout(null);
		
		
		JLabel label = new JLabel(""+friendName);
		label.setBounds(103, 13, 72, 18);
		frmNi.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		ImageIcon mii = new ImageIcon(QqClientFriendList.class.getResource("/img/\u61F5\u903C.jpg"));
		mii.setImage(mii.getImage().getScaledInstance(75,44, Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(mii);
		lblNewLabel.setBounds(14, 0, 75, 44);
		frmNi.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 43, 632, 280);
		frmNi.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击发送信息
				Message ms = new Message();
				
				ms.setMesType(MessageType.message_comm_mes);
				ms.setSender(owner);
				ms.setGetter(friendName);
				ms.setCon(textArea_1.getText());
				ms.setSendTime(new Date().toString());
				
				//发送给服务器
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
					oos.writeObject(ms);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String info = ms.getSender()+"     "+ms.getSendTime()+" \r\n       "+ms.getCon()+"\r\n";
				textArea.append(info);
				textArea_1.setText("");
			}
			
			
		});
		button.setBounds(519, 423, 113, 27);
		frmNi.getContentPane().add(button);
		
		textArea_1 = new JTextArea();
		textArea_1.addKeyListener(new KeyListener()
				{

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						/*if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
						{
							//每当按下backSpace,就撤回到备忘录的内容
							
							//得到对应的负责类,保存到备忘录中
							Caretaker c = ManageQqChatCaretaker.getCaretaker(owner+" "+friendName);
							c.addMemento(saveMemento());
						}*/
						//只要最新的内容跟备忘录中不一样,就把最新的保存到备忘录中
						c = ManageQqChatCaretaker.getCaretaker(owner+" "+friendName);
						if(c.getSize()==0)
							c.addMemento(new Memento(""));
						if(!textArea_1.getText().equals(c.getMemento().getText()))
						{
							//System.out.println("fasdfasfasfas");
							c.addMemento(saveMemento());
						}
						
						//System.out.println("fffffffffffffffffffff");
						
						if ((e.isControlDown() == true)&& (e.getKeyCode() == KeyEvent.VK_Z)) {
							//按下crtl+z键,通过备忘录返回到上一个输入内容
							restoreMemento();
						}
					}

					private Memento saveMemento() {
						// TODO Auto-generated method stub
						return new Memento(textArea_1.getText());
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				});
		textArea_1.setBounds(0, 357, 505, 93);
		frmNi.getContentPane().add(textArea_1);
		
		JButton button_1 = new JButton("\u9009\u62E9\u56FE\u7247");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "暂无时间实现");
			}
		});
		button_1.setBounds(10, 325, 113, 27);
		frmNi.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u9009\u62E9\u6587\u4EF6");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "暂无时间实现");
			}
		});
		button_2.setBounds(132, 325, 113, 27);
		frmNi.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
			}
		});
		button_3.setBounds(519, 383, 113, 27);
		frmNi.getContentPane().add(button_3);
		
		
		
		frmNi.setVisible(true);
	}
	
	//显示新接收的信息
	public void showMessage(Message m)
	{
		String info = "\t\t\t\t   "+m.getSender()+"     "+m.getSendTime()+" \r\n\t\t\t\t       "+m.getCon()+"\r\n";
		this.textArea.append(info);
	}
	
	//撤销
	public void restoreMemento()
	{
		c.delMemento();
		textArea_1.setText("");
		textArea_1.setText(c.getMemento().getText());
		//System.out.println("wwwwwwwwwwwwwwwwwwwwwww");
		c.delMemento();
	}
}
