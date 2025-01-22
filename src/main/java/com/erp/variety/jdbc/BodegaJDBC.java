package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.dao.BodegaDaoRequest;
import com.erp.variety.model.Bodega;
import com.erp.variety.util.SqlConn;

public class BodegaJDBC extends AbstractJDBC{


	@Override
	public List<Bodega> findAll() throws SQLException {
		List<Bodega> listaBodega = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdBodega, Descripcion, UbicacionFi \r\n"
				+ "FROM dbo.Bodega";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Bodega bodega = new Bodega();
				bodega.setIdBodega(rs.getString("IdBodega"));
				bodega.setDescripcion(rs.getString("Descripcion"));
				bodega.setUbicacionFi(rs.getString("UbicacionFi"));
				
				listaBodega.add(bodega);
			}
		} catch (Exception e) {
			listaBodega = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaBodega;
	}

	@Override
	public Bodega getRecord(Object entity) throws SQLException {
		Bodega bodega = (Bodega) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdBodega, Descripcion, UbicacionFi \r\n"
				+ "FROM dbo.Bodega";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				bodega.setIdBodega(rs.getString("idBodega"));
				bodega.setDescripcion(rs.getString("Descripcion"));
				bodega.setUbicacionFi(rs.getString("UbicacionFi"));
			}
		} catch (Exception e) {
			bodega = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bodega;
	}

	@Override
	public String save(Object entity) throws SQLException {
		BodegaDaoRequest bodega = (BodegaDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "INSERT INTO dbo.Bodega(IdBodega,Descripcion, UbicacionFi \r\n"
				+ "VALUES(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bodega.getIdBodega());
			ps.setString(2, bodega.getDescripcion());
			ps.setString(3, bodega.getUbicacionFi());

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			codigoRetorno = String.valueOf(e.getErrorCode());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return codigoRetorno;
	}

	@Override
	public String edit(Object entity) throws SQLException {
		BodegaDaoRequest bodega = (BodegaDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "UPDATE dbo.Bodega\r\n"
				+ "SET Descripcion= ? \r\n"
				+ "UbicacionFi= ? \r\n"
				+ "WHERE IdBodega = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bodega.getIdBodega());
			ps.setString(2, bodega.getDescripcion());
			ps.setString(3, bodega.getUbicacionFi());
		

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			codigoRetorno = String.valueOf(e.getErrorCode());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return codigoRetorno;
	}

	@Override
	public String delete(Object entity) throws SQLException {
		BodegaDaoRequest bodega = (BodegaDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "DELETE FROM dbo.Bodega\r\n"
				+ "WHERE IdBodega = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, bodega.getIdBodega());


			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			codigoRetorno = String.valueOf(e.getErrorCode());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return codigoRetorno;
	}

	@Override
	public String getCorrelativo() throws SQLException {
		String correlativo = "";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT count(idBodega) + 1 as correlativo "
				+ "FROM dbo.Bodega";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				correlativo = rs.getString("correlativo");
			}
		} catch (Exception e) {
			correlativo = "";
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return correlativo;
	}
}
