package com.li.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.li.entity.Book;
import com.li.entity.Car;
import com.li.serviceImp.CarServiceImp;

public class CarServlet extends HttpServlet{
	private static final long serialVersionUID = 6839932729058504547L;
	
	private CarServiceImp carserviceimp = new CarServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		doPost(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String action = request.getParameter("action");
		if(action==null){
			action = "list";
		}
		
		if("add".equals(action)){
			try {
				doadd(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("list".equals(action)){
			dolist(request,response);
		}else if("clear".equals(action)){
			doclear(request,response);
		}else if("modify".equals(action)){
			try {
				domodify(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("delete".equals(action)){
			try {
				dodelete(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void dodelete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取id,在购物车中将该id的商品全部删除
		String sid = request.getParameter("id");
		Car car = null;
		Integer  id = null;
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("car");
		try{
			id = Integer.parseInt(sid.trim());
			if((obj!=null)&&(obj instanceof Car)){
				car = (Car)obj;
			}
			
			//判断购物车是否为空，id是否为空
			
			if(car!=null && id!=null){
				car.remove(id);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e);
		}
		//避免重复提交用重定向
		response.sendRedirect(this.getServletContext().getContextPath()+"/car.do");	}


	private void domodify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("ids");
		String[]  nums = request.getParameterValues("nums");
		//获取购物车
		Car car = null;
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("car");
		if((obj!=null)&&(obj instanceof Car)){
			car = (Car)obj;
			if((ids!=null)&&(ids.length>0)){
				try{
					for(int i=0;i<ids.length;i++){
						Integer id =Integer.parseInt(ids[i]);
						Integer num = Integer.parseInt(nums[i]);
						car.doModify(id,num);
					}
				}catch(Exception e){
					e.printStackTrace();
					throw new Exception(e);
				}
				//重定向到显示页面,避免重复提交
				response.sendRedirect(this.getServletContext().getContextPath()+"/car.do");
			}
		}
		
	}


	private void doclear(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Car car = null;
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("car");
		if((obj != null)&&(obj instanceof 	Car)){
			car =(Car)obj;
		}
		car.getClear();
		response.sendRedirect(this.getServletContext().getContextPath()+"/car.do");
	}


	private void dolist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/car.jsp").forward(request, response);
	}


	private void doadd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String sid = request.getParameter("id");
		Integer bookid = null;
		try{
			bookid = Integer.parseInt(sid);
			Book book = new Book();
			book.setId(bookid);
			if(book!=null){
				findbook(book);
				//System.out.println("book.getBookName():"+book.getBookName());
				Car car = new Car();
				HttpSession session = request.getSession();
				Object obj = session.getAttribute("car");
				if((obj!=null)&&(obj instanceof Car)){
					car = (Car)obj;
//					System.out.println("car:"+car);
				}
				car.add(book);
				//System.out.println("newcar:"+car);
				session.setAttribute("car", car);
				//跳转到info页面
				request.getRequestDispatcher("/carInfo.jsp").forward(request, response);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
		
	}
 

	private void findbook(Book book) {
		try {
			carserviceimp.findbook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
