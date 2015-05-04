package com.suse.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//C3P0工具类：关闭流和取得连接
public final class C3P0Utils {
	private static ComboPooledDataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}

	// 取得连接
	public static Connection getMySqlConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	//取得Datasource
	public static DataSource getDataSource() {
		return dataSource;
	}

	// 关闭连接
	public static void close(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
