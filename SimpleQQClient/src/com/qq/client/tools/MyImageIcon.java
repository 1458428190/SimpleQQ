/**
 * 
 */
package com.qq.client.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.qq.client.view.QqClientFriendList;

/**
 * @author ���̷�
 * ����5:07:38
 * 
 * �Զ����ImageIcon��,��������ӦJlabel
 */
public class MyImageIcon extends ImageIcon{

	public MyImageIcon(String path, int width, int height)
	{

		//super(QqClientFriendList.class.getResource("/img/\u61F5\u903C.jpg"));

		super(path);
		this.setImage(this.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
	}
}
