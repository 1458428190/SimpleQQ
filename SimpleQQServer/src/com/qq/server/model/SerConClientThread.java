/*
 * �ͻ��������������ͨ�ŵ��߳�
 * 
 */
package com.qq.server.model;
import java.io.IOException;
/**
 * @author ���̷�
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
		//�ѷ�������ÿͻ��˵����Ӹ���s;
		this.s = s;
	}
	
	public Socket getS()
	{
		return s;
	}
	
	//֪ͨ�����û�,һ���û�����
	public void notifyOther(String owner)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm = ManageClientThread.hm;
		
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			//ȡ�������˵�id
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
	
	//֪ͨ�����û�,һ���û�������
	public void notifyOtherRemove(String owner)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm = ManageClientThread.hm;
		
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			//ȡ�������˵�id
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
		//���߳̿��Խ��տͻ��˵���Ϣ,��ͣ�ĵȴ��ͻ��˷�����Ϣ
		while(true)
		{
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();       //ͨ��Message����������(˭����˭,���͵�������ʲô,����ʱ��)
				
				//��ms�����ж�,��ʲô���͵���Ϣ
				if(ms.getMesType().equals(MessageType.message_comm_mes))
				{
					//��ͨ��Ϣ��
					
					System.out.println(ms.getSender() +" ������Ϣ "+ms.getCon()+" ��"+ms.getGetter());
					this.sendText(ms.getSender(), ms.getGetter(), ms);
				}
				else
					if(ms.getMesType().equals(MessageType.message_get_onLine))
					{
						//System.out.println(ms.getSender() + "  �ͻ�����������б��");
						//�ͻ��˷����Ļ�ȡ�ں����б��
						SerConClientThread scct = ManageClientThread.getSerConClientThread(ms.getSender());
						//�ҵ����ߺ����б�
						String onLineFriends = ManageClientThread.getAllOnLineUserId();
						//System.out.println("onlineFriends " + onLineFriends);
						Message m = new Message();
						m.setMesType(MessageType.message_ret_onLine);
						m.setCon(onLineFriends);
						m.setGetter(ms.getSender());
						
						//�����ߺ��ѷ����ÿͻ���
						sendMessage(scct, m);
					}
				//��ȡ���к��Ѱ�,���ͻؿͻ���
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
					System.out.println("����֪ͨ");
					//����֪ͨ.
					ManageClientThread.removeUser(ms.getUser());
					//��֪ͨ���������û�
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
		//ȡ�ý����˵�ͨ���߳�
		SerConClientThread scct = ManageClientThread.getSerConClientThread(to);
		//����Ϣת����ȥ,ת������������
		sendMessage(scct, message);
	}

	//ת��
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
