/**
 * 
 */
package com.qq.client.server;

import java.util.HashMap;

import com.qq.client.view.Caretaker;

/**
 * @author 赖程锋
 * 下午12:36:36
 * 
 * 管理聊天时输入聊天内容的备忘录负责人类  , 对于每个了聊天窗口对应于不同的负责人类
 */
public class ManageQqChatCaretaker {
	private static HashMap<String, Caretaker> hm = new HashMap<String, Caretaker>();
	
	//获取,得到某个聊天窗口的备忘录负责类
	public static Caretaker getCaretaker(String ownerAndFriend)
	{
		return (Caretaker)hm.get(ownerAndFriend);
	}
	
	//添加备忘录负责类
	 public static void addCaretaker(String ownerAndFriend, Caretaker c)
	 {
		 hm.put(ownerAndFriend, c);
	 }
}
