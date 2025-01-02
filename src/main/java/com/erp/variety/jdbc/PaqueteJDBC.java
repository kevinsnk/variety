package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Paquete;
import com.erp.variety.model.Zona;
import com.erp.variety.util.SqlConn;

public class PaqueteJDBC extends AbstractJDBC{

	@Override
	public List<Paquete> findAll() throws SQLException {
		List<Paquete> listapaquete = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Paquete";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Paquete paquete = new Paquete();
				paquete.setIdPaquete(rs.getString("IdPaquete"));
				paquete.setDescripcion(rs.getString("Descripcion"));
				paquete.setPCosto(rs.getDouble("pCosto"));
				paquete.setPVenta(rs.getDouble("pVenta"));
				listapaquete.add(paquete);
			}
		} catch (Exception e) {
			listapaquete = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listapaquete;
	}

	@Override
	public Paquete getRecord(Object entity) throws SQLException {
		Paquete paquete = (Paquete) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Paquete";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				paquete.setIdPaquete(rs.getString("IdPaquete"));
				paquete.setDescripcion(rs.getString("Descripcion"));
				paquete.setPCosto(rs.getDouble("pCosto"));
				paquete.setPVenta(rs.getDouble("pVenta"));
			}
		} catch (Exception e) {
			paquete = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return paquete;
	}

	@Override
	public void save(Object entity) throws SQLException {
		Paquete paquete = (Paquete) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.Paquete (IdPaquete, Descripcion, pCosto, pVenta) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete.getIdPaquete());
			ps.setString(2, paquete.getDescripcion());
			ps.setDouble(3, paquete.getPCosto());
			ps.setDouble(4, paquete.getPVenta());
			

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
	}

	@Override
	public void edit(Object entity) throws SQLException {
		Paquete paquete = (Paquete) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Paquete\r\n"
				+ "SET Descripcion = ? \r\n"
				+ "pCosto= ? \r\n"
				+ "pVenta= ? \r\n"
				+ "WHERE IdPaquete = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete.getDescripcion());
			ps.setDouble(2, paquete.getPCosto());
			ps.setDouble(3, paquete.getPVenta());
			ps.setString(4, paquete.getIdPaquete());
			

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
		
	}

	@Override
	public void delete(Object entity) throws SQLException {
		Paquete paquete = (Paquete) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "DELETE FROM dbo.Paquete\r\n"
				+ "WHERE IdPaquete = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete.getIdPaquete());


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
	}
}
