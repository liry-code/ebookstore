package com.li.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.li.entity.Book;
import com.li.util.ConnectionManager;


public class CarDao{

	public void findbook(Book book) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select book.id,book.bookName,book.bookNum,book.bookPrice,book.bookClass_id,book.pic from book where id="+ book.getId();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				book.setId(rs.getInt("id"));
				book.setBookName(rs.getString("bookName"));
				book.setBookNum(rs.getInt("bookNum"));
				book.setBookPrice(rs.getInt("bookPrice"));
				book.getBookcl().setId(rs.getInt("bookClass_id"));
				book.setPicture(rs.getString("pic"));
			}
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
	}
}
