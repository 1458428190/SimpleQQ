/**
 * 
 */
package com.qq.client.server;

import java.util.HashMap;
import java.util.Iterator;

import com.qq.client.view.QqClientFriendList;

/**
 * @author ���̷�
 * ����4:20:08
 * 
 * �������QQ�û��ĺ����б�
 */
public class ManageQqClientFriendList {
	public static HashMap hm2 = new HashMap<String,QqClientFriendList>();
	
	
	//����
	public static void addQqClientFriendList(String owner, QqClientFriendList qcfl)
	{
		//System.out.println("owner " + owner);
		hm2.put(owner, qcfl);
	}
	
	//��ȡ
	public static QqClientFriendList getQqClientFriendList(String owner)
	{
		return (QqClientFriendList)hm2.get(owner);
	}

	/**
	 * @param username
	 * @return
	 */
	public static boolean hasName(String username) {
		
		boolean b = false;
		Iterator it = hm2.keySet().iterator();
		System.out.println(hm2);
		while(it.hasNext())
		{
			System.out.println("aaaaaaaaaaaaaaaaa" + it.next());
			if(it.next().toString().equals(username))
			{
				b = true;
			}
		}
		
		// TODO Auto-generated method stub
		return b;
	}

}
