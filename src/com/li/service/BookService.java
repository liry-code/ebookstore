package com.li.service;

import java.sql.SQLException;
import java.util.List;

import com.li.domain.PageBean;
import com.li.entity.Book;


public interface BookService {
	public List<Book> getAll(Book book, PageBean pagebean) throws SQLException ;
}
