package com.qq.client.model;
/**
 * @author ���̷�
 *
 */
import com.qq.common.User;

public class QqClientUser {
	//����¼��Ϣ,�Ƿ���ڻ����Ƿ񱻶���(����������,�ڷ������м��)
	public User checkUser(User u)
	{
		return new QqClientConServer().SendLoginInfoToServer(u);
	}

}
