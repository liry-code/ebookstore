package com.li.entity;

import java.io.Serializable;


public class Book implements Serializable{
	private static final long serialVersionUID = 9073939552715764755L;
	
	private int id;
	private String bookName;
	private BookClass bookcl = new BookClass();
	private int bookNum;
	private int bookPrice;
	private String picture;
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public BookClass getBookcl() {
		return bookcl;
	}

	public void setBookcl(BookClass bookcl) {
		this.bookcl = bookcl;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", bookcl="
				+ bookcl + ", bookNum=" + bookNum + ", bookPrice=" + bookPrice
				+ ", picture=" + picture + "]";
	}
}
