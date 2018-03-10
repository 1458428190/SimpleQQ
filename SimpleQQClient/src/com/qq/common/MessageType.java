/**
 * 2017/12/07
 */
package com.qq.common;

/**
 * @author 赖程锋
 * 下午1:30:11
 * 信息包类型接口
 */
public interface MessageType {
	String message_login = "login";                  //登录请求包
	String message_succeed = "loginSuccess";       //登录成功
	String message_login_fail="loginFalse";       //登录失败
	String message_comm_mes="commMessage";       //普通信息包
	String message_get_onLine="get_onLineFriend";      //要求好友在线包
	String message_ret_onLine="ret_onLineFriend";       //返回好友在线包
	String message_get_friends = "get_friends";      //请求或者所有好友包
	String message_register="register";            //注册用户包
	String message_gaimi ="gaimi";               //改密包
	String message_chongfu="chongfu";           //重复登录确认包
	String message_xiaxian = "xiaxian";        //告知服务器,该QQ已下线
	String message_ret_xiaxian = "ret_xiaxian";  //服务器通知在线用户,某个人已下线
}
