/**
 * 
 */
package com.qq.client.server;

import java.util.HashMap;

import com.qq.client.view.Caretaker;

/**
 * @author ���̷�
 * ����12:36:36
 * 
 * ��������ʱ�����������ݵı���¼��������  , ����ÿ�������촰�ڶ�Ӧ�ڲ�ͬ�ĸ�������
 */
public class ManageQqChatCaretaker {
	private static HashMap<String, Caretaker> hm = new HashMap<String, Caretaker>();
	
	//��ȡ,�õ�ĳ�����촰�ڵı���¼������
	public static Caretaker getCaretaker(String ownerAndFriend)
	{
		return (Caretaker)hm.get(ownerAndFriend);
	}
	
	//��ӱ���¼������
	 public static void addCaretaker(String ownerAndFriend, Caretaker c)
	 {
		 hm.put(ownerAndFriend, c);
	 }
}
