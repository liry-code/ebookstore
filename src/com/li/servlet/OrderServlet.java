package com.li.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.li.util.StringUtil;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = -5817926815256919004L;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String action = request.getParameter("action");
		if(StringUtil.isBlank(action))
			action="list";
		if("list".equals(action))
			doList(request,response);
	}

	public  void doList(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//Ìø×ªÒ³Ãæ
		request.getRequestDispatcher("/order/list.jsp").forward(request, response);
	}
}
