/**
 * 
 */
package com.qq.client.server;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.common.Message;
import com.qq.common.MessageType;

/**
 * @author ���̷�
 * ����4:04:46
 * 
 * ��װ����ͼ�к����б�Ĳ���
 */
public class FriendListServer {
	
	
	//����һ�����ߺ����б������
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
	
	//����һ����ȡ���к����б�������
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
