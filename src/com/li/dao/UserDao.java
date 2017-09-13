package com.li.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.li.entity.User;
import com.li.util.ConnectionManager;

public class UserDao{

	public void add(User user) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		//动态sql语句
		StringBuilder s1 = new StringBuilder("insert into user(id,username,password,repassword,realname,sex,addr,tel");
		StringBuilder s2 = new StringBuilder("    values(null,?,?,?,?,?,?,?");
		List<Object> list =new ArrayList<>();
		list.add(user.getUsername());
		list.add(user.getPassword());
		list.add(user.getRepassword());
		list.add(user.getRealname());
		list.add(user.getSex());
		list.add(user.getAddr());
		list.add(user.getTel());
		if((user.getMailcode()!=null) && (user.getMailcode().trim().length()>0)){
			s1.append(",mailcode");
			s2.append(",?");
			list.add(user.getMailcode());
		}
		//考虑输入时不能输入空值，所以必须使用trim()判断
		if((user.getEmail()!=null) &&(user.getEmail().trim().length()>0)){
			s1.append(",email");
			s2.append(",?");
			list.add(user.getEmail());
		}
		s1.append(")");
		String sql = s1.toString()+s2.toString()+")";
				//"insert into user(id,username,password,repassword,realname,sex,addr,mailcode,tel,email) values(null,?,?,?,?,?,?,?,?,?)";
		try{
			ps = conn.prepareStatement(sql);
			if((list!=null)&&(list.size()>0)){
				//遍历集合
				for(int i=0;i<list.size();i++){
					ps.setObject(i+1, list.get(i));
				}
			}
			//System.out.println("sql:"+sql);
			int kk = ps.executeUpdate();
//			if(kk>0){ 
//				System.out.println("注册成功："+kk);
//			}
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException(e);
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
	}

	public User isExist(String username) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User loginUser = null;
		try{
			String sql ="select * from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			//System.out.println(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				loginUser = new User();
				loginUser.setUsername(rs.getString("username"));
				loginUser.setPassword(rs.getString("password"));
			}
			//System.out.println(flag);
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException(e);
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return loginUser;
	}
	

	public Boolean doLogin(User loginUser) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		//默认为false,表示用户登录失败
		Boolean flag = false;
		try{
			String sql ="select * from user where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUsername());
			ps.setString(2, loginUser.getPassword());
			//System.out.println(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
				loginUser.setUsername(rs.getString("username"));
				loginUser.setTel(rs.getString("tel"));
				loginUser.setSex(rs.getBoolean("sex"));
				loginUser.setRepassword(rs.getString("repassword"));
				loginUser.setRealname(rs.getString("realname"));
				loginUser.setPassword(rs.getString("password"));
				loginUser.setMailcode(rs.getString("mailcode"));
				loginUser.setEmail(rs.getString("email"));
				loginUser.setAddr(rs.getString("addr"));
			}
			//System.out.println(flag);
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException(e);
		}finally{
			ConnectionManager.closeConnection(conn);
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		}
		return flag;
	}
}
