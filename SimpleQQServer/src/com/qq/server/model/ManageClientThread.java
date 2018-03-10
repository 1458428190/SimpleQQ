package com.qq.server.model;
/**
 * @author 赖程锋
 *
 */
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.User;

public class ManageClientThread {
	public static HashMap hm = new HashMap<String, SerConClientThread>();
	//向hm中添加一个客户端线程
	public static void addSerConClientTherad(String userid, SerConClientThread scct)
	{
		hm.put(userid, scct);
	}
	
	public static SerConClientThread getSerConClientThread(String userid)
	{
		return (SerConClientThread) hm.get(userid);
	}

	//返回当前在线的人的情况(还没有对每个用户的好友进行划分)
	public static String getAllOnLineUserId()
	{
		//得到所有在线用户的名  ,用空格隔开
		Iterator it = hm.keySet().iterator();
		
		String res = "";
		while(it.hasNext())
		{
			res += it.next().toString()+" ";
		}
		return res;
	}
	
	//判断一个人是否在线
	public static boolean isOnline(String username)
	{
		boolean b = false;
		//得到所有在线用户的名  ,用空格隔开
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
	
	//某个人下线,断开连接
	public static void removeUser(User u)
	{
		String username = u.getUsername();
		hm.remove(username);
	}
}
