/**
 * 
 */
package com.qq.client.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.qq.client.view.QqClientFriendList;

/**
 * @author 赖程锋
 * 下午5:07:38
 * 
 * 自定义的ImageIcon类,用于自适应Jlabel
 */
public class MyImageIcon extends ImageIcon{

	public MyImageIcon(String path, int width, int height)
	{

		//super(QqClientFriendList.class.getResource("/img/\u61F5\u903C.jpg"));

		super(path);
		this.setImage(this.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
	}
}
