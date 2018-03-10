/**
 * 
 */
package com.qq.client.server;

import java.util.HashMap;
import java.util.Iterator;

import com.qq.client.view.QqClientFriendList;

/**
 * @author 赖程锋
 * 下午4:20:08
 * 
 * 管理各个QQ用户的好友列表
 */
public class ManageQqClientFriendList {
	public static HashMap hm2 = new HashMap<String,QqClientFriendList>();
	
	
	//加入
	public static void addQqClientFriendList(String owner, QqClientFriendList qcfl)
	{
		//System.out.println("owner " + owner);
		hm2.put(owner, qcfl);
	}
	
	//获取
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
