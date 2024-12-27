package com.erp.variety;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.erp.variety.util.SqlConn;

@SpringBootApplication
public class VarietyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VarietyApplication.class, args);
		
//		System.out.println("SE PRUEBA CONEXION A BASE");
//		String pruebaConexion = null;
//		try {
//			pruebaConexion = pruebaConexion();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(pruebaConexion);
	}
	
	public static String pruebaConexion() {
		String respuestaDB = "";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		try {
			st = conn.createStatement();
	        rs = st.executeQuery("SELECT 'hello' as PRUEBA");
	        while(rs.next()) {
	        	respuestaDB = rs.getString("PRUEBA");
	        }
		} catch (Exception e) {
			e.printStackTrace();
			respuestaDB  = "Error " + e.getMessage();
		}finally {
	        try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        System.out.println("Connection Closed....");
		}
		
		return respuestaDB;
	}

}
