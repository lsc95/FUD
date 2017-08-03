package com.coderli.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

public class DBUtils {
	private static String url = null;
	private static String name = null;
	private static String pwd = null;
	static {
		Properties properties = new Properties();
		try {
			properties.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			String type = properties.getProperty("dbtype");
			url = properties.getProperty(type + "url");
			name = properties.getProperty(type + "name");
			pwd = properties.getProperty(type + "pwd");
			// 加载驱动
			Class.forName(properties.getProperty(type + "driver"));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private DBUtils() {
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, name, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取uuid
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	// 获取preparedStatement
	public static PreparedStatement getPreparedStatement(String sql,
			Connection conn) {
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取statement对象
	public static Statement getStatement(Connection conn) {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 绑定参数
	public static void bindParams(PreparedStatement ps, Object... objects) {
		for (int i = 0; i <objects.length; i++) {
			try {
				ps.setObject((i+1), objects[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭资源
	public static void closeAll(AutoCloseable... closeables) {
		for (AutoCloseable closeable : closeables) {
			try {
				if (closeable != null)
					closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static int executeDML(String sql,Object...objects ) {
		Connection conn=getConnection();
		PreparedStatement ps = getPreparedStatement(sql, conn);
		int i=-1;
		try {
			bindParams(ps, objects);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(ps,conn);
		}
		return i;
	}
}
