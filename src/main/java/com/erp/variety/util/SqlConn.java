package com.erp.variety.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlConn {

	
	public Connection getConnection() {
		Connection conn = null;
		try {
			System.out.println("ENTRA A SQLCONN");
			Properties properties = new Properties();
			properties = getProperties();
			
			String driver = properties.getProperty("DB_DRIVER");
			String url = properties.getProperty("DB_URL");
			String user = properties.getProperty("DB_USER");
			String password = properties.getProperty("DB_PSW");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		    System.out.println("CONEXION EXITOSA");
		} catch (Exception e) {
			System.out.println("ERROR EN CONEXIÓN A BASE DE DATOS");
			e.printStackTrace();
		}
		return conn;
	}
	
	private Properties getProperties() throws IOException {
		Properties prop = new Properties();
		System.out.println("entra al getProperties");
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		prop.load(new FileInputStream(rootPath + "config/application.properties"));


		return prop;
	}
}
