package com.li.servlet;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.li.entity.BookClass;
import com.li.serviceImp.BookClassServiceImp;

public class BookClassServlet extends HttpServlet{
	private static final long serialVersionUID = 1840211537255057057L;
	
	private static BookClassServiceImp bookclassSI = new BookClassServiceImp();
	
	public List<BookClass> getAllClass() throws ServletException{
		List<BookClass> bookclasslist = bookclassSI.getAllClass();
		return bookclasslist;
	}
	
	public static void main(String[] args) throws ServletException {
		List<BookClass> bookclasslist = bookclassSI.getAllClass();
		for (BookClass bc : bookclasslist) {
			System.out.println(bc);
		}
	}
}
