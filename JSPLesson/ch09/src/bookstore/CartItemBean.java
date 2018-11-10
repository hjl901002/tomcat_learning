package org.huangjl.ch09.bookstore;

import java.io.Serializable;

public class CartItemBean implements Serializable{
	
	private BookBean book = null;
	
	//��ʾѡ����ͼ�������
	private int quantity = 0;
	
	public CartItemBean() {
		
	}
	
	public CartItemBean(BookBean book) {
		this.book  = book;
		this.quantity = 1;
	}
	
	public BookBean getBook() {
		return book;
	}
	
	public void setBook(BookBean book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	*  �õ�����Ŀ����ͼ����ܼ۸��ܼ� = ͼ��ĵ��� * ������
	*/
	public float getItemPrice() {
		float price = book.getPrice() * quantity;
		long val = Math.round(price * 100);
		return val/100.0f;
	}
}