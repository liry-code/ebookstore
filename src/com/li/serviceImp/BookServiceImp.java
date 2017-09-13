package com.li.serviceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Request;

import com.li.dao.BookDao;
import com.li.domain.PageBean;
import com.li.entity.Book;
import com.li.service.BookService;

public class BookServiceImp implements BookService{
	
	private BookDao bookdao =  new BookDao();
	//�������Book����ļ���
	public List<Book> getAll(Book book, PageBean pagebean) throws SQLException {
		//��booK������ӵ�bookList������
		return bookdao.getAll(book ,pagebean);
	}
}
