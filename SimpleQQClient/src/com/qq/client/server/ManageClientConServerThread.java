package com.qq.client.server;
/**
 * @author ���̷�
 *
 */
import java.util.HashMap;

public class ManageClientConServerThread {
	public static HashMap hm = new HashMap<String,ClientConServerThread>();
	//���̼߳��������
	public static void addClientConServerThread(String qqid, ClientConServerThread ccst)
	{
		hm.put(qqid,ccst);
	}
	
	public static ClientConServerThread getClientConServerThread(String qqid)
	{
		return (ClientConServerThread)hm.get(qqid);
	}
	
}
