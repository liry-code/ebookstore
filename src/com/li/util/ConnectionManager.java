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


//properties�ļ�����͸��ļ���ͬһ������
public class ConnectionManager {
	//˽�й��췽������֤����
	private ConnectionManager(){}
	
	//�����̳߳�
	//����propreties����,properties�����ļ���ż�ֵ��
	public static final int poolNum = 10;
	private static final Properties pp = new Properties();
	private static final List<Connection> pool = Collections
			.synchronizedList(new ArrayList<Connection>());
	
	//��̬�����(jdbc����������ķ�ʱ��)
	static{
		try {
			loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//��������
	private static void loadProperties() throws IOException, ClassNotFoundException{
		if(pp.isEmpty()){
			//��ppΪ��ʱ�ż���
			//���������ļ�
			InputStream is = ConnectionManager.class.getResourceAsStream("sql.properties");
			pp.load(is);
			String driver = pp.getProperty("driver");
			Class.forName(driver);
		}
	}
	
	//��������
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
	
	//�ͷ���Դ
	public static void closeConnection(Connection conn) throws SQLException{
		if(pool.size()>poolNum){
			conn.close();
		}else{
			pool.add(conn);
		}
	}
}
