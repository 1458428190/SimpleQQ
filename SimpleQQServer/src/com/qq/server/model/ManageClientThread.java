package com.qq.server.model;
/**
 * @author ���̷�
 *
 */
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.User;

public class ManageClientThread {
	public static HashMap hm = new HashMap<String, SerConClientThread>();
	//��hm�����һ���ͻ����߳�
	public static void addSerConClientTherad(String userid, SerConClientThread scct)
	{
		hm.put(userid, scct);
	}
	
	public static SerConClientThread getSerConClientThread(String userid)
	{
		return (SerConClientThread) hm.get(userid);
	}

	//���ص�ǰ���ߵ��˵����(��û�ж�ÿ���û��ĺ��ѽ��л���)
	public static String getAllOnLineUserId()
	{
		//�õ����������û�����  ,�ÿո����
		Iterator it = hm.keySet().iterator();
		
		String res = "";
		while(it.hasNext())
		{
			res += it.next().toString()+" ";
		}
		return res;
	}
	
	//�ж�һ�����Ƿ�����
	public static boolean isOnline(String username)
	{
		boolean b = false;
		//�õ����������û�����  ,�ÿո����
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext())
		{
			if(it.next().equals(username))
			{
				b = true;
				break;
			}
		}
		return b;
	}
	
	//ĳ��������,�Ͽ�����
	public static void removeUser(User u)
	{
		String username = u.getUsername();
		hm.remove(username);
	}
}
