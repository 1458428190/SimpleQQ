/**
 * 
 */
package com.qq.server.model;

import com.qq.common.Message;

/**
 * @author Àµ³Ì·æ
 * ÉÏÎç1:10:14
 */
public interface AbstractChatroom {
	public void sendText(String from, String to, Message message);
}
