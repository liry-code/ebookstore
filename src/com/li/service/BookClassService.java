package com.li.service;

import java.util.List;

import javax.servlet.ServletException;

import com.li.entity.BookClass;

public interface BookClassService {
	public List<BookClass> getAllClass() throws ServletException;
	
	public BookClass getBookClass(Integer bookclassid);
	
}
