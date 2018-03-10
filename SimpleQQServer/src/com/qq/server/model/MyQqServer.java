package com.qq.server.model;
import java.io.IOException;
/**
 * @author 赖程锋
 *
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.server.db.QqUserDao;

public class MyQqServer {

	ServerSocket ss;
	private QqUserDao qud = new QqUserDao();
	public MyQqServer()
	{
		try
		{
			System.out.println("我是服务器,我在9999端口处监听");
			//在9999处端口上监听
			ss = new ServerSocket(9999);
			//阻塞,如果第一次输入错误的密码,可以接着下一次输入,直至成功(失败时,重新监听)
			while(true)
			{
			Socket s = ss.accept();
			
			//接收客户端发来的信息
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			
			if(ms.getMesType().equals(MessageType.message_login))
			{
			//System.out.println(u);
			Message m = new Message();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			User u = ms.getUser();
			//System.out.println("uuuuuuuuu = "+u);
			//改成数据库,登录请求
			User user = qud.loginCheck(u);
			
			System.out.println("use " + user);
			if(user!=null)
			{
				if(ManageClientThread.isOnline(user.getUsername()))
				{
					m.setMesType(MessageType.message_chongfu);
					m.setUser(user);
					oos.writeObject(m);
				}
				else
				{
					m.setMesType(MessageType.message_succeed);
					m.setUser(user);
					oos.writeObject(m);
					
					//单开一个线程,让该线程与客户端保持通信
					SerConClientThread scct = new SerConClientThread(s);
					//加进线程管理中
					ManageClientThread.addSerConClientTherad(user.getUsername(), scct);
					//启动与该客户端通信的线程
					scct.start();
					
					//并通知其他在线用户
					scct.notifyOther(user.getUsername());
				}
			}
			else
			{
				m.setMesType(MessageType.message_login_fail);
				oos.writeObject(m);
				s.close();   //释放监听资源
			}
			}
			//注册请求
			else if(ms.getMesType().equals(MessageType.message_register))
			{
				User u = ms.getUser();
				String res = qud.addQqUser(u);
				System.out.println("res   " + res);
				//发送确认包
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				Message m = new Message();
				m.setMesType(MessageType.message_register);
				m.setCon(res);
				oos.writeObject(m);
				s.close();
			}
			//改密请求,密码带在ms的con中
			else if(ms.getMesType().equals(MessageType.message_gaimi))
			{
				String res="";
				User u = ms.getUser();
				ms.setCon(u.getPassword());
				if(qud.updatePassword(u, ms.getCon()))
					res = "success";
				else
					res = "fail";
				
				//发送确认包
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				Message m = new Message();
				m.setMesType(MessageType.message_gaimi);
				m.setCon(res);
				oos.writeObject(m);
				s.close();
			}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeSocket()
	{
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
