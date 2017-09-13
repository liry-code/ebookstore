package com.li.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.li.domain.PageBean;
import com.li.entity.Book;
import com.li.util.ConnectionManager;

public class BookDao{
	
	// 实现对图书信息的查询
	@SuppressWarnings("resource")
	public List<Book> getAll(Book book0, PageBean pagebean) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		//首先进行判断bookclassid是否为空：为空则将数据库中的数据全部输出
		//若非空则将对应的图书数据输出
		StringBuilder sql =new StringBuilder("( select b.*,bc.className from Book b left join bookClass bc on b.bookClass_id=bc.id where 1=1 ");
		if(book0!=null){
			if(book0.getBookcl().getId()!=null){
				sql.append(" and b.bookClass_id=").append(book0.getBookcl().getId());
			}
			if(book0.getBookName()!=null){
				sql.append("  and b.bookName like '%").append(book0.getBookName()).append("%'");
			}
		}
		// 统计图书总数,即最大行数
		int counter = 0;
		try {
//			//创建一个t_book表
//			ps = conn.prepareStatement(sql);
//			ps.executeUpdate();
//			sql = "select * from t_book where 1=1 ";
			
			//System.out.println("book0.getBookcl()："+book0.getBookcl());
			//System.out.println("------------------------");
			
			// 1》当book.getBookcl不为空时，则进行类别查询（没有模糊条件）
			//new Book()对象会产生一个bookClass对象，即使没有bookclassid属性，Book.getBookcl也会有地址
			
			//如下是使用产生虚拟的t_table表，运行时会报错
//			if(book0!=null){
//				//当book0对象存在时，book0对象中会有bookName属性或者bookclassid属性（二者是对立存在）
//				//System.out.println("book0:"+book0.getBookName());
//				if(book0.getBookName()!=null){
//					// 2》当book.getBookcl为空时，说明bookClass为空，则进行模糊查询
//					sql = sql + " and t_book.bookName like  '%" + book0.getBookName() + "%' ";
//					//System.out.println(sql);
//				}else if(book0.getBookcl().getId()!=null){
//					sql = sql + " and t_book.bookClass_id = " + book0.getBookcl().getId() ;
//					//System.out.println(sql);
//				}
//				if((book0.getBookcl().getId()!=null)&&(book0.getBookName()!=null)){
//					sql = sql + " and t_book.bookClass_id = " + book0.getBookcl().getId()  + " and t_book.bookName like  '%" + book0.getBookName() + "%' ";
//					//System.out.println(sql);
//				}
			
				//改进如下
				String ss=" select count(*)  "+ sql.substring(sql.indexOf(" from "));
				ps=conn.prepareStatement(ss);
				rs=ps.executeQuery();
				if(rs.next()){
					counter=rs.getInt(1);
				}
				//System.out.println("获取数据库数据量" + counter);
				// 设置最大行数
				pagebean.setTotalRows(counter);
				
				// 每页的最大行数在bookServlet中已经实现
				// 设置最大页码数 ------- 记性判断是否整除
				if (counter % pagebean.getPerPageRow() == 0) {
					pagebean.setMaxPage(counter / pagebean.getPerPageRow());
					//System.out.println("设置最大页码:"+pagebean.getMaxPage());
				} else {
					pagebean.setMaxPage(counter / pagebean.getPerPageRow() + 1);
					//System.out.println("设置最大页码+1:"+pagebean.getMaxPage());
				}
			
				sql.insert(0, " select * from (select rownum rn,t.* from " );
				sql.append(" ) t where rownum <=").append((pagebean.getMaxPage()*pagebean.getCurrentPageNum())).append(") where rn>").append((pagebean.getCurrentPageNum()-1)*pagebean.getPerPageRow());
				
//			//System.out.println("获取当前页码" + pagebean.getCurrentPageNum());
//			// 判断当前页是不是末尾页或者整除页
//			if (pagebean.getCurrentPageNum() != pagebean.getMaxPage()
//					|| counter % pagebean.getPerPageRow() == 0) {
//				sql = sql + " limit "
//						+ (pagebean.getCurrentPageNum() - 1)* pagebean.getPerPageRow() + " , "+ pagebean.getPerPageRow() ;
//				//System.out.println(sql);
//			} else {
//				sql = sql + " limit "
//						+ (pagebean.getCurrentPageNum() - 1)* pagebean.getPerPageRow() + " , " + counter% pagebean.getPerPageRow() ;
//				//System.out.println(sql);
//			}
			System.out.println(sql);
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setBookName(rs.getString("bookName"));
				// book.getBookcl获取图书类别对象
				book.getBookcl().setId(rs.getInt("bookClass_id"));
				book.getBookcl().setClassName(rs.getString("className"));
				book.setBookNum(rs.getInt("bookNum"));
				book.setBookPrice(rs.getInt("bookPrice"));
				book.setPicture(rs.getString("pic"));
				//System.out.println("book"+book);
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		//System.out.println(bookList.size());
		return bookList;
	}
}
