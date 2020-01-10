package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	private static Logger logger = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() {
		
		String url = "jdbc:postgresql://localhost:5432/";
		String username = "postgres";
		String password = "";
		
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.warn("Unable to obtain connection to database", e);
		}
		//System.out.println(con);
		return con;
	}
}
