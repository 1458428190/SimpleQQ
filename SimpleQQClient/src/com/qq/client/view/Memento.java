/**
 * 
 */
package com.qq.client.view;

/**
 * @author 赖程锋
 * 下午12:27:05
 * 
 * 这是一个备忘录类
 */
class Memento {
	private String text;   //在文本框输入的内容

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param text
	 */
	public Memento(String text) {
		super();
		this.text = text;
	}
	

}
