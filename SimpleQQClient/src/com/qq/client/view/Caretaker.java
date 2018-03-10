/**
 * 
 */
package com.qq.client.view;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ���̷�
 * ����12:28:16
 * 
 * ����һ����������, ���������¼��
 */
public class Caretaker {
	private List<Memento> mementoList = new ArrayList<Memento>();
	private int size = 20;         //�����Գ����ص�20�β���֮ǰ��.
	
	//�õ�ǰһ������¼
	public Memento getMemento()
	{
		//��ȡǰһ��,���Ƴ�
		Memento memento = mementoList.get(mementoList.size()-1);  
		return memento;
	}
	
	//�Ƴ�ǰһ��
	public void delMemento()
	{
		mementoList.remove(mementoList.size()-1);
	}
	
	//�õ�����¼�Ĵ�С
	public int getSize()
	{
		return mementoList.size();
	}
	
	//����һ��������¼
	public void addMemento(Memento memento)
	{
		mementoList.add(memento);
		if(mementoList.size()==21)
		{
			//�Ƴ��������һ��
			mementoList.remove(0);
		}
	}

}
