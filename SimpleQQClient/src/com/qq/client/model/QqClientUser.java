package com.qq.client.model;
/**
 * @author 赖程锋
 *
 */
import com.qq.common.User;

public class QqClientUser {
	//检查登录信息,是否存在或者是否被冻结(发给服务器,在服务器中检查)
	public User checkUser(User u)
	{
		return new QqClientConServer().SendLoginInfoToServer(u);
	}

}
