/*
 * 这是客户端和服务端保持通信的线程
 */
package com.qq.client.server;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.qq.client.view.QqClientChat;
import com.qq.client.view.QqClientFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class ClientConServerThread extends Thread{
	private Socket s;
	
	public ClientConServerThread(Socket s)
	{
		this.s = s;
	}

	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端读取的消息;
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();
				//收到普通信息
				if(ms.getMesType().equals(MessageType.message_comm_mes))
				{
					System.out.println(ms.getGetter()+"读取到从服务发来的消息"+ms.getSender()+"  "+ms.getCon());
					
					//得到该聊天界面
					QqClientChat qcc = ManageQqChat.getQqClientChat(ms.getGetter()+" "+ms.getSender());
					qcc.showMessage(ms);
				}
				else if(ms.getMesType().equals(MessageType.message_ret_onLine))
				{
					
					//System.out.println("收到好友列表" + ms.getCon());
					//收到在线好友列表包
					String con = ms.getCon();
					
					String friends[] = con.split(" ");
					String getter = ms.getGetter();
					//得到该用户的好友列表界面
					QqClientFriendList qcfl = ManageQqClientFriendList.getQqClientFriendList(getter);
					if(qcfl!=null)
						qcfl.updateFriends(ms);
				}
				else if(ms.getMesType().equals(MessageType.message_register))
				{
					//回馈的注册是否成功包
					String con = ms.getCon();
					if(con.equals("registerSuccess"))
					{
						//注册成功
						JOptionPane.showMessageDialog(null, "注册成功!QQ号是:"+ms.getUser().getUseId());
					}
				}
				else if(ms.getMesType().equals(MessageType.message_get_friends))
				{
					//回馈所有好友
					String con = ms.getCon();
					//System.out.println("con = "+con);
					QqClientFriendList qcfl = ManageQqClientFriendList.getQqClientFriendList(ms.getGetter());
					qcfl.showFriends(ms.getCon());
				}
				else if(ms.getMesType().equals(MessageType.message_ret_xiaxian))
				{
					//收到服务器中某个用户已下线
					String con = ms.getCon();
					QqClientFriendList qcfl = ManageQqClientFriendList.getQqClientFriendList(ms.getGetter());
					qcfl.removeFriends(ms.getCon());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	public Socket getS()
	{
		return this.s;
	}
}
