package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Zona;
import com.erp.variety.util.SqlConn;

public class ZonaJDBC extends AbstractJDBC{


	@Override
	public List<Zona> findAll() throws SQLException {
		List<Zona> listazona = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Zona";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Zona zona = new Zona();
				zona.setIdzona(rs.getString("Idzona"));
				zona.setDescripcion(rs.getString("Descripcion"));
				zona.setIdEmpleado(rs.getInt("idEmpleado"));
				listazona.add(zona);
			}
		} catch (Exception e) {
			listazona = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listazona;
	}

	@Override
	public Zona getRecord(Object entity) throws SQLException {
		Zona zona = (Zona) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Cliente";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				zona.setIdzona(rs.getString("idzona"));
				zona.setDescripcion(rs.getString("Descripcion"));
				zona.setIdEmpleado(rs.getInt("idEmpleado"));
			}
		} catch (Exception e) {
			zona = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return zona;
	}

	@Override
	public String save(Object entity) throws SQLException {
		Zona zona = (Zona) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.Zona(Idzona,Descripcion,idEmpleado) VALUES(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, zona.getIdzona());
			ps.setString(2, zona.getDescripcion());
			ps.setInt(3, zona.getIdEmpleado());
			

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return codigoRespuesta;
	}

	@Override
	public String edit(Object entity) throws SQLException {
		Zona zona = (Zona) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Zona\r\n"
				+ "SET Descripcion = ? \r\n"
				+ "idEmpleado= ? \r\n"
				+ "WHERE IdZona = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, zona.getDescripcion());
			ps.setInt(2, zona.getIdEmpleado());
			

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return codigoRespuesta;
	}

	@Override
	public String delete(Object entity) throws SQLException {
		Zona zona = (Zona) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "DELETE FROM dbo.Zona\r\n"
				+ "WHERE IdZona = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, zona.getIdzona());


			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return codigoRespuesta;
	}

	@Override
	public String getCorrelativo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
