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
	
	// ʵ�ֶ�ͼ����Ϣ�Ĳ�ѯ
	@SuppressWarnings("resource")
	public List<Book> getAll(Book book0, PageBean pagebean) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		//���Ƚ����ж�bookclassid�Ƿ�Ϊ�գ�Ϊ�������ݿ��е�����ȫ�����
		//���ǿ��򽫶�Ӧ��ͼ���������
		StringBuilder sql =new StringBuilder("( select b.*,bc.className from Book b left join bookClass bc on b.bookClass_id=bc.id where 1=1 ");
		if(book0!=null){
			if(book0.getBookcl().getId()!=null){
				sql.append(" and b.bookClass_id=").append(book0.getBookcl().getId());
			}
			if(book0.getBookName()!=null){
				sql.append("  and b.bookName like '%").append(book0.getBookName()).append("%'");
			}
		}
		// ͳ��ͼ������,���������
		int counter = 0;
		try {
//			//����һ��t_book��
//			ps = conn.prepareStatement(sql);
//			ps.executeUpdate();
//			sql = "select * from t_book where 1=1 ";
			
			//System.out.println("book0.getBookcl()��"+book0.getBookcl());
			//System.out.println("------------------------");
			
			// 1����book.getBookcl��Ϊ��ʱ�����������ѯ��û��ģ��������
			//new Book()��������һ��bookClass���󣬼�ʹû��bookclassid���ԣ�Book.getBookclҲ���е�ַ
			
			//������ʹ�ò��������t_table������ʱ�ᱨ��
//			if(book0!=null){
//				//��book0�������ʱ��book0�����л���bookName���Ի���bookclassid���ԣ������Ƕ������ڣ�
//				//System.out.println("book0:"+book0.getBookName());
//				if(book0.getBookName()!=null){
//					// 2����book.getBookclΪ��ʱ��˵��bookClassΪ�գ������ģ����ѯ
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
			
				//�Ľ�����
				String ss=" select count(*)  "+ sql.substring(sql.indexOf(" from "));
				ps=conn.prepareStatement(ss);
				rs=ps.executeQuery();
				if(rs.next()){
					counter=rs.getInt(1);
				}
				//System.out.println("��ȡ���ݿ�������" + counter);
				// �����������
				pagebean.setTotalRows(counter);
				
				// ÿҳ�����������bookServlet���Ѿ�ʵ��
				// �������ҳ���� ------- �����ж��Ƿ�����
				if (counter % pagebean.getPerPageRow() == 0) {
					pagebean.setMaxPage(counter / pagebean.getPerPageRow());
					//System.out.println("�������ҳ��:"+pagebean.getMaxPage());
				} else {
					pagebean.setMaxPage(counter / pagebean.getPerPageRow() + 1);
					//System.out.println("�������ҳ��+1:"+pagebean.getMaxPage());
				}
			
				sql.insert(0, " select * from (select rownum rn,t.* from " );
				sql.append(" ) t where rownum <=").append((pagebean.getMaxPage()*pagebean.getCurrentPageNum())).append(") where rn>").append((pagebean.getCurrentPageNum()-1)*pagebean.getPerPageRow());
				
//			//System.out.println("��ȡ��ǰҳ��" + pagebean.getCurrentPageNum());
//			// �жϵ�ǰҳ�ǲ���ĩβҳ��������ҳ
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
				// book.getBookcl��ȡͼ��������
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
