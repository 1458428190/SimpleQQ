/*
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.qq.client.model;
import java.io.IOException;
/**
 * @author ���̷�
 *
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.qq.client.server.ClientConServerThread;
import com.qq.client.server.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientConServer {
	
	public Socket s;
	/*public QqClientConServer()
	{
		try {
			Socket s = new Socket("127.0.0.1", 9999);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}*/
	//���͵�һ������, ����ɹ�,�򷵻�һ���������û���Ϣ����
	public User SendLoginInfoToServer(Object u)
	{
		boolean b = false;
		User user=null;
		try {
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			Message m = new Message();
			m.setMesType(MessageType.message_login);
			m.setUser((User)u);
			
			//System.out.println("uuu  = "+m.getUser());
			
			oos.writeObject(m);
			
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			//System.out.println("cussss");
			
			//System.out.println(ms.getUser());
			if(ms.getMesType().equals("loginSuccess"))
			{
				//��ʾ��¼�ɹ�,����һ���̸߳����û�������
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				user=ms.getUser();
				String friends = ms.getCon();
				ManageClientConServerThread.addClientConServerThread(user.getUsername(), ccst);
				//System.out.println("name= "+((User)u).getUsername());
			}
			else
				//�����ظ���¼
				if(ms.getMesType().equals(MessageType.message_chongfu))
				{
					User usernull = new User();
					usernull.setUseId("??????");
					return usernull;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return user;
	}

	//���͸�����������Ϣ,����boolֵ,����������֪ͨ
	public String SendUpdateInfo(User u, String messageType)
	{
		String res = "fail";
		ObjectOutputStream oos;
		try {
			//�½�һ��Socket
			s = new Socket("127.0.0.1", 9999);
			
			//����
			Message ms = new Message();
			ms.setUser(u);
			ms.setMesType(messageType);
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(ms);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message) ois.readObject();
			
			//�����ע��,����һ���µ�QQ��
			if(!m.getCon().equals("fail")&&messageType.equals(MessageType.message_register))
			{
				res = m.getCon();
			}
			else if(m.getCon().equals("success")&&messageType.equals(MessageType.message_gaimi))
			{
				res = "true";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return res;
	}
}
