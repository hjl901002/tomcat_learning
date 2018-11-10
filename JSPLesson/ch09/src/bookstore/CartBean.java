package org.huangjl.ch09.bookstore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import java.io.Serializable;

public class CartBean implements Serializable{
	
	private HashMap<Integer, CartItemBean> items = null;
	
	private int numOfItems = 0;
	
	public CartBean() {
		items = new HashMap<Integer, CartItemBean>();
	}
	
	/**
	* �ڹ��ﳵ������һ����Ŀ��������ﳵ���Ѿ����ڸ���Ŀ����ʲôҲ����
	*
	*/
	public synchronized void addItem(Integer bookId, BookBean book) {
		if(!items.containsKey(bookId)) {
			CartItemBean item = new CartItemBean(book);
			items.put(bookId, item);
			numOfItems++;
		}
	}
	
	/**
	* �ӹ��ﳵɾ��һ��ͼ����Ŀ
	* 
	*/
	public synchronized void deleteItem(Integer bookId) {
		if(items.containsKey(bookId)) {
			items.remove(bookId);
			numOfItems--;
		}
	}
	
	/**
	* ������ﳵ�����е�ͼ����Ŀ
	*
	*/
	public synchronized void clear() {
		items.clear();
		numOfItems = 0;
	}
	
	/**
	* �õ����ﳵ��ͼ����Ŀ������
	* 
	*/
	public synchronized int getNumOfItems() {
		return numOfItems;
	}
	
	/**
	* ����ĳͼ��Ĺ�������
	* 
	*/
	public synchronized void setItemNum(Integer bookId, int quantity) {
		if(items.containsKey(bookId)) {
			CartItemBean item = (CartItemBean)items.get(bookId);
			//������õ�ͼ������Ϊ0����С��0����ɾ�����ﳵ����Ӧ��ͼ����Ŀ
			if(quantity <= 0)
				items.remove(bookId);
			else
				item.setQuantity(quantity);
		}
	}
	
	/**
	* �õ����ﳵ������ͼ��ļ۸�
	* 
	*/
	public synchronized float getTotalPrice() {
		float amount = 0.0f;
		Iterator<CartItemBean> it = items.values().iterator();
		while(it.hasNext()) {
			CartItemBean item = (CartItemBean)it.next();
			amount += item.getItemPrice();
		}
		return amount;
	}
	
	/**
	* �õ����ﳵ�����е�ͼ����Ŀ
	* 
	*/
	public synchronized Collection<CartItemBean> getItems() {
		return items.values();
	}
	
	/**
	* �ж�ͼ���Ƿ��Ѿ����빺�ﳵ��
	* 
	*/
	public synchronized boolean isExist(Integer bookId) {
		if(items.containsKey(bookId))
			return true;
		else
			return false;
	}
}