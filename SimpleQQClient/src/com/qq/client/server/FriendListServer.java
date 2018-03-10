/**
 * 
 */
package com.qq.client.server;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.common.Message;
import com.qq.common.MessageType;

/**
 * @author 赖程锋
 * 下午4:04:46
 * 
 * 封装了视图中好友列表的操作
 */
public class FriendListServer {
	
	
	//发送一个在线好友列表请求包
	public void SendFriendListServer(String userId)
	{
		try {
			Socket s = ManageClientConServerThread.getClientConServerThread(userId).getS();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			Message m = new Message();
			m.setMesType(MessageType.message_get_onLine);
			m.setSender(userId);
			oos.writeObject(m);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}
	
	//发送一个获取所有好友列表的请求包
	public void SendAllFriends(String userId)
	{
		try
		{
			System.out.println("userId = "+userId);
			Socket s = ManageClientConServerThread.getClientConServerThread(userId).getS();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			Message ms = new Message();
			ms.setMesType(MessageType.message_get_friends);
			ms.setSender(userId);
			oos.writeObject(ms);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
