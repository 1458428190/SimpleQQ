/**
 * 
 */
package com.qq.client.view;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赖程锋
 * 下午12:28:16
 * 
 * 这是一个负责人类, 负责管理备忘录类
 */
public class Caretaker {
	private List<Memento> mementoList = new ArrayList<Memento>();
	private int size = 20;         //不可以撤销回到20次操作之前的.
	
	//得到前一个备忘录
	public Memento getMemento()
	{
		//获取前一个,并移除
		Memento memento = mementoList.get(mementoList.size()-1);  
		return memento;
	}
	
	//移除前一个
	public void delMemento()
	{
		mementoList.remove(mementoList.size()-1);
	}
	
	//得到备忘录的大小
	public int getSize()
	{
		return mementoList.size();
	}
	
	//加入一个备备忘录
	public void addMemento(Memento memento)
	{
		mementoList.add(memento);
		if(mementoList.size()==21)
		{
			//移除掉最早的一个
			mementoList.remove(0);
		}
	}

}
