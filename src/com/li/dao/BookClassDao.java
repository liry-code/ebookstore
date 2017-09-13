package com.li.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.li.entity.BookClass;
import com.li.util.ConnectionManager;

public class BookClassDao{

	public List<BookClass> getAllClass() throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookClass> bookclasslist =new ArrayList<BookClass>();
		BookClass bookclass = null;
		try{
			String sql = "select * from bookClass";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				bookclass = new BookClass();
				bookclass.setId(rs.getInt("id"));
				bookclass.setClassName(rs.getString("className"));
				bookclasslist.add(bookclass);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return bookclasslist;
	}

	public BookClass getBookClass(Integer bookclassid) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookClass bookclass = null;
		try{
			String sql = "select * from bookClass where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookclassid);
			rs = ps.executeQuery();
			if(rs.next()){
				bookclass = new BookClass();
				bookclass.setId(rs.getInt("id"));
				bookclass.setClassName(rs.getString("className"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return bookclass;
	}
}
