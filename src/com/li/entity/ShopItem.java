package com.li.entity;

import java.io.Serializable;


//shopItem项，记录购买商品的数量和商品信息
public class ShopItem implements Serializable{
	private static final long serialVersionUID = -5883011638397990031L;
	private Book book;
	private int num;
	
	public int getAllPrice(){
		return this.num*this.book.getBookPrice();
	}
	
	public ShopItem(Book book, int num) {
		this.book = book;
		this.num = num;
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
}
