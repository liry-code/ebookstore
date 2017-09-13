package com.li.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.li.domain.PageBean;
import com.li.entity.Book;
import com.li.entity.BookClass;
import com.li.serviceImp.BookClassServiceImp;
import com.li.serviceImp.BookServiceImp;

public class BookServlet extends HttpServlet{
	
	private static final long serialVersionUID = -345013210307904692L;

	
	//创建bookServiceImp对象
	BookServiceImp bookserviceimp = new BookServiceImp();
	BookClassServiceImp bookclassserviceimp =  new BookClassServiceImp();
	protected void doGet(HttpServletRequest reques, HttpServletResponse response)
			throws ServletException, IOException {
		reques.setCharacterEncoding("utf8");
		doPost(reques, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String action = request.getParameter("action");
		if(action==null){
			action = "list";
		}
		if("list".equals(action)){
			doList(request,response);
		}
	}


	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//将上次登录时间从cookie中取出
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//获取上次登录时间
		String nowTime = null;
		Cookie[] cookies = request.getCookies();
		//对cookies进行容错判断
		if(cookies!=null && cookies.length>0){
			for (Cookie cookie : cookies) {
				//cookie里面存的是键值对
				if("nowTime".equals(cookie.getName())){
					nowTime = cookie.getValue();
					break;
				}
			}
		}
		
		//如果本次cookie记录的登录时间不为空时，则将当上次登录时间赋值给lastTime
		String lastTime = null;
		if(nowTime!=null){
			lastTime = nowTime;
		}
		
		//设置当前登录时间
		nowTime = df.format(new Date());
		//将当前时间存放在cookie中
		Cookie c1 = new Cookie("nowTime", nowTime);
		//设置cookie保存当前时间和上次登录时间
		c1.setMaxAge(Integer.MAX_VALUE);
		
		//保存上次登录时间
		Cookie c2 = new Cookie("lastTime", lastTime);
		//设置cookie保存当前时间和上次登录时间
		c2.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(c1);
		response.addCookie(c2);
		
		
		
		
		
		//获取要显示图书的信息
		List<Book> bookList = new ArrayList<Book>();
		PageBean pagebean =  new PageBean();
		
		//从list.jsp页面获取当前页码值
		String sCurrentPageNum = request.getParameter("currentPageNum");
		//判断sCurrentPageNum是否为空,如果不为空，则将sCurrentPageNum强转为int，   如果为空则currentPageNum默认为1
		int currentPage = 1;
		if(sCurrentPageNum!=null){
			try{
				currentPage = Integer.parseInt(sCurrentPageNum);
			}catch(Exception e){
				currentPage=1;
			}
		}
		//设置当前页码值
		pagebean.setCurrentPageNum(currentPage);
		
		//设置每页行数
		String sperPageRow = this.getServletConfig().getInitParameter("perPageRow");
		int perPageRow = 1;
		try{
			perPageRow = Integer.parseInt(sperPageRow);
		}catch(Exception e){
			//进行容错
			perPageRow = 1;
		}
		//设置每页行数
		pagebean.setPerPageRow(perPageRow);
		//获取要获取图书信息的类别
		String sbookclassid = request.getParameter("bookclassid");
		//System.out.println("bookclassid:"+sbookclassid);
		Integer bookclassid = null;
		try{
			bookclassid = Integer.parseInt(sbookclassid);
			//将bookclassid值设置为属性值，为了在list集合显示图书信息时显示该类图书的类别
			//返回一个bookClass对象，获取bookclass的id对应的className
			
			BookClass bookclass = bookclassserviceimp.getBookClass(bookclassid);
			
			request.setAttribute("bookclass", bookclass);
		}catch(Exception e){
			bookclassid = null;
		}
		//System.out.println("bookclassid:"+bookclassid);
		
		//获取要查询图书信息的关键字
		//创建一个Book对象，可以存储迷糊查询条件(bookName)和图书类别
		Book book = null;      //以免查询所有图书时，sql语句出现错误
		//先获取图书的类型，在设置其bookClass对象的id
		
		
		String keyword = request.getParameter("keyword");
		//request.setCharacterEncoding("utf8");
		request.setAttribute("keyword", keyword);
		//System.out.println(keyword);
		if(keyword != null){
			//在list页面中，keyword是通过post提交数据的，因此要进行编码格式转换
			if("get".equalsIgnoreCase(request.getMethod())){
				keyword = new String(keyword.getBytes("iso8859-1"),"utf8");
			}
			keyword = keyword.trim();
			//System.out.println("keyword:"+keyword);
		}
		//keyword如果为null，则不进行模糊查询操作，反之进行模糊查询
		if((keyword != null ) || ( bookclassid != null)){
			//new Book()对象会产生一个bookClass对象
			book = new Book();
			if((keyword != null)&&(bookclassid==null)){
				book.setBookName(keyword);
				book.getBookcl().setId(null);
			}
			
			if((bookclassid != null)&&(keyword==null)){
				book.getBookcl().setId(bookclassid);
				book.setBookName(null);
			}
			
			if((bookclassid != null)&&(keyword!=null)){
				book.setBookName(keyword);
				book.getBookcl().setId(bookclassid);
			}
		}
		//System.out.println(book.getBookName()+"------"+book.getBookcl().getId());
		
		try {
			bookList = bookserviceimp.getAll(book,pagebean);
			//System.out.println(bookList);
			//将总行数发送list.jsp页面
			request.setAttribute("pagebean", pagebean);
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		BookServiceImp bookserviceimp = new BookServiceImp();
		PageBean pagebean = new PageBean();
		pagebean.setPerPageRow(2);
		pagebean.setCurrentPageNum(1);
		List<Book> booklist= bookserviceimp.getAll(null,pagebean);
		
		for (Book book : booklist) {
			System.out.println("---------------------------");
			System.out.println(book);
		}
	}
}
