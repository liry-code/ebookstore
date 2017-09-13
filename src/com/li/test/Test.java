package com.li.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Test {
	public static void main(String[] args) throws Exception {
		try{
			long nowtime = System.currentTimeMillis();
			Class.forName("com.mysql.jdbc.Driver");
			
			long nowtime1 = System.currentTimeMillis();
			Connection conn = DriverManager.getConnection("jdbc:mysql:///jdbc","root","root");
			long nowtime2 = System.currentTimeMillis();
			String sql = "insert into work(id,name,age) values(null,'li',12) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			long nowtime3 = System.currentTimeMillis();
			System.out.println("nowtime:"+nowtime);
			System.out.println("加载驱动："+(nowtime1-nowtime));
			System.out.println("建立连接："+(nowtime2-nowtime1));
			System.out.println("程序执行时间："+(nowtime3-nowtime2));
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
