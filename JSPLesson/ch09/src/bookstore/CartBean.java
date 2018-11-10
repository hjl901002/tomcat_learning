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
	* 在购物车中增加一个条目，如果购物车中已经存在该条目，则什么也不做
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
	* 从购物车删除一个图书条目
	* 
	*/
	public synchronized void deleteItem(Integer bookId) {
		if(items.containsKey(bookId)) {
			items.remove(bookId);
			numOfItems--;
		}
	}
	
	/**
	* 清除购物车中所有的图书条目
	*
	*/
	public synchronized void clear() {
		items.clear();
		numOfItems = 0;
	}
	
	/**
	* 得到购物车中图书条目的总数
	* 
	*/
	public synchronized int getNumOfItems() {
		return numOfItems;
	}
	
	/**
	* 设置某图书的购买数量
	* 
	*/
	public synchronized void setItemNum(Integer bookId, int quantity) {
		if(items.containsKey(bookId)) {
			CartItemBean item = (CartItemBean)items.get(bookId);
			//如果设置的图书数量为0或者小于0，则删除购物车中相应的图书条目
			if(quantity <= 0)
				items.remove(bookId);
			else
				item.setQuantity(quantity);
		}
	}
	
	/**
	* 得到购物车中所有图书的价格
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
	* 得到购物车中所有的图书条目
	* 
	*/
	public synchronized Collection<CartItemBean> getItems() {
		return items.values();
	}
	
	/**
	* 判断图书是否已经加入购物车中
	* 
	*/
	public synchronized boolean isExist(Integer bookId) {
		if(items.containsKey(bookId))
			return true;
		else
			return false;
	}
}