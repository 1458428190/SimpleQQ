/**
 * 
 */
package com.qq.client.server;

import java.util.HashMap;

import com.qq.client.view.QqClientChat;

/**
 * @author ���̷�
 *	����: �����������,   ĳĳ��ĳĳ�����촰��(��ֹ�ظ�,�����˵���ģʽ)
 */
public class ManageQqChat {
	private static HashMap hm = new HashMap<String, QqClientChat>();
	//����
	public static void addQqClientChat(String ownerAndFriendName, QqClientChat qcc)
	{
		hm.put(ownerAndFriendName, qcc);
	}
	
	//���
	public static QqClientChat getQqClientChat(String ownerAndFriendName)
	{
		return (QqClientChat)hm.get(ownerAndFriendName);
	}
	

}
