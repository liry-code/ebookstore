package com.li.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


//properties文件必须和该文件在同一个包下
public class ConnectionManager {
	//私有构造方法，保证单例
	private ConnectionManager(){}
	
	//创建线程池
	//创建propreties对象,properties配置文件存放键值对
	public static final int poolNum = 10;
	private static final Properties pp = new Properties();
	private static final List<Connection> pool = Collections
			.synchronizedList(new ArrayList<Connection>());
	
	//静态代码块(jdbc建立连接最耗费时间)
	static{
		try {
			loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//加载驱动
	private static void loadProperties() throws IOException, ClassNotFoundException{
		if(pp.isEmpty()){
			//当pp为空时才加载
			//加载配置文件
			InputStream is = ConnectionManager.class.getResourceAsStream("sql.properties");
			pp.load(is);
			String driver = pp.getProperty("driver");
			Class.forName(driver);
		}
	}
	
	//建立连接
	public static Connection getConnection(){
		Connection conn = null;
		if(pool.isEmpty()){
			String url = pp.getProperty("url");
			String username = pp.getProperty("username");
			String password = pp.getProperty("password");
			try {
				conn  = DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			conn = pool.remove(pool.size()-1);
		}
		return conn;
	}
	
	//释放资源
	public static void closeConnection(Connection conn) throws SQLException{
		if(pool.size()>poolNum){
			conn.close();
		}else{
			pool.add(conn);
		}
	}
}
