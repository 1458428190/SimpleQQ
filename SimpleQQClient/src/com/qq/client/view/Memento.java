/**
 * 
 */
package com.qq.client.view;

/**
 * @author ���̷�
 * ����12:27:05
 * 
 * ����һ������¼��
 */
class Memento {
	private String text;   //���ı������������

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
