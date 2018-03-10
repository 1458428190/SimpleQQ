package com.qq.server.model;
import java.io.IOException;
/**
 * @author ���̷�
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
			System.out.println("���Ƿ�����,����9999�˿ڴ�����");
			//��9999���˿��ϼ���
			ss = new ServerSocket(9999);
			//����,�����һ��������������,���Խ�����һ������,ֱ���ɹ�(ʧ��ʱ,���¼���)
			while(true)
			{
			Socket s = ss.accept();
			
			//���տͻ��˷�������Ϣ
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			
			if(ms.getMesType().equals(MessageType.message_login))
			{
			//System.out.println(u);
			Message m = new Message();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			User u = ms.getUser();
			//System.out.println("uuuuuuuuu = "+u);
			//�ĳ����ݿ�,��¼����
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
					
					//����һ���߳�,�ø��߳���ͻ��˱���ͨ��
					SerConClientThread scct = new SerConClientThread(s);
					//�ӽ��̹߳�����
					ManageClientThread.addSerConClientTherad(user.getUsername(), scct);
					//������ÿͻ���ͨ�ŵ��߳�
					scct.start();
					
					//��֪ͨ���������û�
					scct.notifyOther(user.getUsername());
				}
			}
			else
			{
				m.setMesType(MessageType.message_login_fail);
				oos.writeObject(m);
				s.close();   //�ͷż�����Դ
			}
			}
			//ע������
			else if(ms.getMesType().equals(MessageType.message_register))
			{
				User u = ms.getUser();
				String res = qud.addQqUser(u);
				System.out.println("res   " + res);
				//����ȷ�ϰ�
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				Message m = new Message();
				m.setMesType(MessageType.message_register);
				m.setCon(res);
				oos.writeObject(m);
				s.close();
			}
			//��������,�������ms��con��
			else if(ms.getMesType().equals(MessageType.message_gaimi))
			{
				String res="";
				User u = ms.getUser();
				ms.setCon(u.getPassword());
				if(qud.updatePassword(u, ms.getCon()))
					res = "success";
				else
					res = "fail";
				
				//����ȷ�ϰ�
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
