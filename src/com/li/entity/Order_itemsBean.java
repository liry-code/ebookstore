package com.li.entity;

import java.io.Serializable;

public class Order_itemsBean implements Serializable{
	private static final long serialVersionUID = 3796968580978187963L;
	
	private int id;
	private User user= new User();
	private int price;
	private int num;
	private Book_orderBean book_order= new Book_orderBean();
	
	
	public Book_orderBean getBook_order() {
		return book_order;
	}
	public void setBook_order(Book_orderBean book_order) {
		this.book_order = book_order;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
