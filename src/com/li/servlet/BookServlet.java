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

	
	//����bookServiceImp����
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
		//���ϴε�¼ʱ���cookie��ȡ��
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//��ȡ�ϴε�¼ʱ��
		String nowTime = null;
		Cookie[] cookies = request.getCookies();
		//��cookies�����ݴ��ж�
		if(cookies!=null && cookies.length>0){
			for (Cookie cookie : cookies) {
				//cookie�������Ǽ�ֵ��
				if("nowTime".equals(cookie.getName())){
					nowTime = cookie.getValue();
					break;
				}
			}
		}
		
		//�������cookie��¼�ĵ�¼ʱ�䲻Ϊ��ʱ���򽫵��ϴε�¼ʱ�丳ֵ��lastTime
		String lastTime = null;
		if(nowTime!=null){
			lastTime = nowTime;
		}
		
		//���õ�ǰ��¼ʱ��
		nowTime = df.format(new Date());
		//����ǰʱ������cookie��
		Cookie c1 = new Cookie("nowTime", nowTime);
		//����cookie���浱ǰʱ����ϴε�¼ʱ��
		c1.setMaxAge(Integer.MAX_VALUE);
		
		//�����ϴε�¼ʱ��
		Cookie c2 = new Cookie("lastTime", lastTime);
		//����cookie���浱ǰʱ����ϴε�¼ʱ��
		c2.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(c1);
		response.addCookie(c2);
		
		
		
		
		
		//��ȡҪ��ʾͼ�����Ϣ
		List<Book> bookList = new ArrayList<Book>();
		PageBean pagebean =  new PageBean();
		
		//��list.jspҳ���ȡ��ǰҳ��ֵ
		String sCurrentPageNum = request.getParameter("currentPageNum");
		//�ж�sCurrentPageNum�Ƿ�Ϊ��,�����Ϊ�գ���sCurrentPageNumǿתΪint��   ���Ϊ����currentPageNumĬ��Ϊ1
		int currentPage = 1;
		if(sCurrentPageNum!=null){
			try{
				currentPage = Integer.parseInt(sCurrentPageNum);
			}catch(Exception e){
				currentPage=1;
			}
		}
		//���õ�ǰҳ��ֵ
		pagebean.setCurrentPageNum(currentPage);
		
		//����ÿҳ����
		String sperPageRow = this.getServletConfig().getInitParameter("perPageRow");
		int perPageRow = 1;
		try{
			perPageRow = Integer.parseInt(sperPageRow);
		}catch(Exception e){
			//�����ݴ�
			perPageRow = 1;
		}
		//����ÿҳ����
		pagebean.setPerPageRow(perPageRow);
		//��ȡҪ��ȡͼ����Ϣ�����
		String sbookclassid = request.getParameter("bookclassid");
		//System.out.println("bookclassid:"+sbookclassid);
		Integer bookclassid = null;
		try{
			bookclassid = Integer.parseInt(sbookclassid);
			//��bookclassidֵ����Ϊ����ֵ��Ϊ����list������ʾͼ����Ϣʱ��ʾ����ͼ������
			//����һ��bookClass���󣬻�ȡbookclass��id��Ӧ��className
			
			BookClass bookclass = bookclassserviceimp.getBookClass(bookclassid);
			
			request.setAttribute("bookclass", bookclass);
		}catch(Exception e){
			bookclassid = null;
		}
		//System.out.println("bookclassid:"+bookclassid);
		
		//��ȡҪ��ѯͼ����Ϣ�Ĺؼ���
		//����һ��Book���󣬿��Դ洢�Ժ���ѯ����(bookName)��ͼ�����
		Book book = null;      //�����ѯ����ͼ��ʱ��sql�����ִ���
		//�Ȼ�ȡͼ������ͣ���������bookClass�����id
		
		
		String keyword = request.getParameter("keyword");
		//request.setCharacterEncoding("utf8");
		request.setAttribute("keyword", keyword);
		//System.out.println(keyword);
		if(keyword != null){
			//��listҳ���У�keyword��ͨ��post�ύ���ݵģ����Ҫ���б����ʽת��
			if("get".equalsIgnoreCase(request.getMethod())){
				keyword = new String(keyword.getBytes("iso8859-1"),"utf8");
			}
			keyword = keyword.trim();
			//System.out.println("keyword:"+keyword);
		}
		//keyword���Ϊnull���򲻽���ģ����ѯ��������֮����ģ����ѯ
		if((keyword != null ) || ( bookclassid != null)){
			//new Book()��������һ��bookClass����
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
			//������������list.jspҳ��
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
