package com.li.entity;

import java.io.Serializable;

public class Book_orderBean implements Serializable{
	private static final long serialVersionUID = -4789071214152581167L;

	private int id;
	private Book book = new Book();
	private int num;
	private int price;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
