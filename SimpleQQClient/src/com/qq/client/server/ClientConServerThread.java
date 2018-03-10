/*
 * ���ǿͻ��˺ͷ���˱���ͨ�ŵ��߳�
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
			//��ͣ�Ķ�ȡ�ӷ������˶�ȡ����Ϣ;
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message)ois.readObject();
				//�յ���ͨ��Ϣ
				if(ms.getMesType().equals(MessageType.message_comm_mes))
				{
					System.out.println(ms.getGetter()+"��ȡ���ӷ���������Ϣ"+ms.getSender()+"  "+ms.getCon());
					
					//�õ����������
					QqClientChat qcc = ManageQqChat.getQqClientChat(ms.getGetter()+" "+ms.getSender());
					qcc.showMessage(ms);
				}
				else if(ms.getMesType().equals(MessageType.message_ret_onLine))
				{
					
					//System.out.println("�յ������б�" + ms.getCon());
					//�յ����ߺ����б��
					String con = ms.getCon();
					
					String friends[] = con.split(" ");
					String getter = ms.getGetter();
					//�õ����û��ĺ����б����
					QqClientFriendList qcfl = ManageQqClientFriendList.getQqClientFriendList(getter);
					if(qcfl!=null)
						qcfl.updateFriends(ms);
				}
				else if(ms.getMesType().equals(MessageType.message_register))
				{
					//������ע���Ƿ�ɹ���
					String con = ms.getCon();
					if(con.equals("registerSuccess"))
					{
						//ע��ɹ�
						JOptionPane.showMessageDialog(null, "ע��ɹ�!QQ����:"+ms.getUser().getUseId());
					}
				}
				else if(ms.getMesType().equals(MessageType.message_get_friends))
				{
					//�������к���
					String con = ms.getCon();
					//System.out.println("con = "+con);
					QqClientFriendList qcfl = ManageQqClientFriendList.getQqClientFriendList(ms.getGetter());
					qcfl.showFriends(ms.getCon());
				}
				else if(ms.getMesType().equals(MessageType.message_ret_xiaxian))
				{
					//�յ���������ĳ���û�������
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
