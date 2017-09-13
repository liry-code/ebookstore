package com.li.serviceImp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.li.dao.BookClassDao;
import com.li.entity.BookClass;
import com.li.service.BookClassService;

public class BookClassServiceImp implements BookClassService {

	private BookClassDao bookclassdao = new BookClassDao();
	public List<BookClass> getAllClass() throws ServletException {
		List<BookClass> bookclasslist  = new ArrayList<BookClass>();
		try{
			bookclasslist = bookclassdao.getAllClass();
		}catch(Exception e){
			System.out.println("bookClassServiceImp");
			throw new ServletException(e);
		}
		return bookclasslist;
	}
	
	//��ȡidΪbookclassid��bookClass����
	public BookClass getBookClass(Integer bookclassid) {
		BookClass bookclass = null;
		try{
			bookclass = bookclassdao.getBookClass(bookclassid);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return bookclass;
	}
}
