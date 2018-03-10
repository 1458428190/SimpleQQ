/**
 * 
 */
package com.qq.client.server;

import java.util.HashMap;

import com.qq.client.view.QqClientChat;

/**
 * @author 赖程锋
 *	功能: 管理聊天界面,   某某对某某的聊天窗口(防止重复,利用了单例模式)
 */
public class ManageQqChat {
	private static HashMap hm = new HashMap<String, QqClientChat>();
	//加入
	public static void addQqClientChat(String ownerAndFriendName, QqClientChat qcc)
	{
		hm.put(ownerAndFriendName, qcc);
	}
	
	//获得
	public static QqClientChat getQqClientChat(String ownerAndFriendName)
	{
		return (QqClientChat)hm.get(ownerAndFriendName);
	}
	

}
