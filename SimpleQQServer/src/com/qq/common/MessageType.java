/**
 * 2017/12/07
 */
package com.qq.common;

/**
 * @author ���̷�
 * ����1:30:11
 * ��Ϣ�����ͽӿ�
 */
public interface MessageType {
	String message_login = "login";                  //��¼�����
	String message_succeed = "loginSuccess";       //��¼�ɹ�
	String message_login_fail="loginFalse";       //��¼ʧ��
	String message_comm_mes="commMessage";       //��ͨ��Ϣ��
	String message_get_onLine="get_onLineFriend";      //Ҫ��������߰�
	String message_ret_onLine="ret_onLineFriend";       //���غ������߰�
	String message_get_friends = "get_friends";      //����������к��Ѱ�
	String message_register="register";            //ע���û���
	String message_gaimi ="gaimi";               //���ܰ�
	String message_chongfu="chongfu";           //�ظ���¼ȷ�ϰ�
	String message_xiaxian = "xiaxian";        //��֪������,��QQ������
	String message_ret_xiaxian = "ret_xiaxian";  //������֪ͨ�����û�,ĳ����������
}
