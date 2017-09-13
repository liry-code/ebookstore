package com.li.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import com.li.entity.BookClass;
import com.li.servlet.BookClassServlet;

public class BookClassListener implements ServletContextListener{
	
	BookClassServlet bookClassServlet = new BookClassServlet();
	
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		try {
			List<BookClass> bookClassList = bookClassServlet.getAllClass();
			application.setAttribute("bookclasslist", bookClassList);
		} catch (ServletException e) {
			application.log((this.getClass().getName()),e);
		}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		application.removeAttribute("bookclasslist");
	}
}
