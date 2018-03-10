/*
 * 客户端与服务器保持通信的线程
 * 
 */
package com.qq.server.model;
import java.io.IOException;
/**
 * @author 赖程锋
 *
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.server.db.QqUserDao;

public class SerConClientThread extends Thread implements AbstractChatroom{
	Socket s;
	QqUserDao qud = new QqUserDao();
	
	public SerConClientThread(Socket s)
	{
		//把服务器与该客户端的连接赋给s;
		this.s = s;
	}
	
	public Socket getS()
	{
		return s;
	}
	
	//通知其他用户,一个用户上线
	public void notifyOther(String owner)
	{
		//得到所有在线的人的线程
		HashMap hm = ManageClientThread.hm;
		
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			//取出在线人的id
			String onLineUserId = it.next().toString();
			
			//System.out.println("is = " + onLineUserId);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getSerConClientThread(onLineUserId).getS().getOutputStream());
				Message m = new Message();
				m.setMesType(MessageType.message_ret_onLine);
				m.setCon(owner);
				m.setGetter(onLineUserId);
				oos.writeObject(m);
				
				//System.out.println(m);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//通知其他用户,一个用户以下线
	public void notifyOtherRemove(String owner)
	{
		//得到所有在线的人的线程
		HashMap hm = ManageClientThread.hm;
		
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			//取出在线人的id
			String onLineUserId = it.next().toString();
			
			//System.out.println("is = " + onLineUserId);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getSerConClientThread(onLineUserId).getS().getOutputStream());
				Message m = new Message();
				m.setMesType(MessageType.message_ret_xiaxian);
				m.setCon(owner);
				m.setGetter(onLineUserId);
				oos.writeObject(m);
				
				//System.out.println(m);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run()
	{
		//此线程可以接收客户端的信息,不停的等待客户端发送信息
		while(true)
		{
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();       //通过Message对象来交互(谁发给谁,发送的内容是什么,或者时间)
				
				//对ms进行判断,是什么类型的信息
				if(ms.getMesType().equals(MessageType.message_comm_mes))
				{
					//普通信息包
					
					System.out.println(ms.getSender() +" 发送消息 "+ms.getCon()+" 给"+ms.getGetter());
					this.sendText(ms.getSender(), ms.getGetter(), ms);
				}
				else
					if(ms.getMesType().equals(MessageType.message_get_onLine))
					{
						//System.out.println(ms.getSender() + "  客户端请求好友列表包");
						//客户端发来的获取在好友列表包
						SerConClientThread scct = ManageClientThread.getSerConClientThread(ms.getSender());
						//找到在线好友列表
						String onLineFriends = ManageClientThread.getAllOnLineUserId();
						//System.out.println("onlineFriends " + onLineFriends);
						Message m = new Message();
						m.setMesType(MessageType.message_ret_onLine);
						m.setCon(onLineFriends);
						m.setGetter(ms.getSender());
						
						//把在线好友发给该客户端
						sendMessage(scct, m);
					}
				//获取所有好友包,发送回客户端
				else if(ms.getMesType().equals(MessageType.message_get_friends))
					{
						Message m = new Message();
						m.setGetter(ms.getSender());
						m.setMesType(MessageType.message_get_friends);
						m.setCon(qud.getAllFriend());
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						oos.writeObject(m);
					}
				else if(ms.getMesType().equals(MessageType.message_xiaxian))
				{
					System.out.println("下线通知");
					//下线通知.
					ManageClientThread.removeUser(ms.getUser());
					//并通知所有在线用户
					notifyOtherRemove(ms.getUser().getUsername());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/* (non-Javadoc)
	 * @see com.qq.server.model.AbstractChatroom#sendText(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendText(String from, String to, Message message) {
		// TODO Auto-generated method stub
		//取得接收人的通信线程
		SerConClientThread scct = ManageClientThread.getSerConClientThread(to);
		//把信息转发出去,转发到接收者中
		sendMessage(scct, message);
	}

	//转发
	public void sendMessage(SerConClientThread scct, Message m)
	{
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(scct.s.getOutputStream());
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
