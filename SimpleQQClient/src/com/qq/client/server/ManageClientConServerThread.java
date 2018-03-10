package com.qq.client.server;
/**
 * @author 赖程锋
 *
 */
import java.util.HashMap;

public class ManageClientConServerThread {
	public static HashMap hm = new HashMap<String,ClientConServerThread>();
	//把线程加入管理中
	public static void addClientConServerThread(String qqid, ClientConServerThread ccst)
	{
		hm.put(qqid,ccst);
	}
	
	public static ClientConServerThread getClientConServerThread(String qqid)
	{
		return (ClientConServerThread)hm.get(qqid);
	}
	
}
