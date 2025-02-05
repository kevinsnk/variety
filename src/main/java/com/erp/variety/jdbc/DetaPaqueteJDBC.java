package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.DetaPaquete;
import com.erp.variety.model.Paquete;
import com.erp.variety.util.SqlConn;

public class DetaPaqueteJDBC extends AbstractJDBC {

	@Override
	public List<?> findAll() throws SQLException {
		List<DetaPaquete> listapaquete = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdDetaPaquete, idPaquete, idProducto, detaProducto, pcosto, pventa, cantidad, idbodega \r\n"
				+ "FROM detapaquete";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				DetaPaquete detaPaquete = new DetaPaquete();
				detaPaquete.setIdDetaPaquete(rs.getInt("IdDetaPaquete"));
				detaPaquete.setIdPaquete(rs.getString("idPaquete"));
				detaPaquete.setIdProducto(rs.getString("idProducto"));
				detaPaquete.setDetaProducto(rs.getString("detaProducto"));
				detaPaquete.setCosto(rs.getBigDecimal("pcosto"));
				detaPaquete.setVenta(rs.getBigDecimal("pventa"));
				detaPaquete.setCantidad(rs.getInt("cantidad"));
				detaPaquete.setIdBodega(rs.getInt("idbodega"));
				listapaquete.add(detaPaquete);
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
	public Object getRecord(Object entity) throws SQLException {
		Paquete paquete = (Paquete) entity;
		DetaPaquete detaPaquete = new DetaPaquete();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		ResultSet rs;
		String query = "SELECT IdDetaPaquete, idPaquete, idProducto, detaProducto, pcosto, pventa, cantidad, idbodega \r\n"
				+ "FROM detapaquete \r\n"
				+ "WHERE idPaquete = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete.getIdPaquete());
			rs = ps.executeQuery(query);
			if (rs.next()) {
				detaPaquete.setIdDetaPaquete(rs.getInt("IdDetaPaquete"));
				detaPaquete.setIdPaquete(rs.getString("idPaquete"));
				detaPaquete.setIdProducto(rs.getString("idProducto"));
				detaPaquete.setDetaProducto(rs.getString("detaProducto"));
				detaPaquete.setCosto(rs.getBigDecimal("pcosto"));
				detaPaquete.setVenta(rs.getBigDecimal("pventa"));
				detaPaquete.setCantidad(rs.getInt("cantidad"));
				detaPaquete.setIdBodega(rs.getInt("idbodega"));
			}
		} catch (Exception e) {
			detaPaquete = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return detaPaquete;
	}

	@Override
	public String getCorrelativo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Object entity) throws SQLException {
		DetaPaquete detaPaquete = (DetaPaquete) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.detaPaquete (idPaquete, idProducto, detaProducto, pcosto, pventa, cantidad, idbodega) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.setInt(1, detaPaquete.getIdDetapaquete());
			ps.setString(1, detaPaquete.getIdPaquete());
			ps.setString(2, detaPaquete.getIdProducto());
			ps.setString(3, detaPaquete.getDetaProducto());
			ps.setBigDecimal(4, detaPaquete.getCosto());
			ps.setBigDecimal(5, detaPaquete.getVenta());
			ps.setDouble(6, detaPaquete.getCantidad());
			ps.setDouble(7, detaPaquete.getIdBodega());
			

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			codigoRespuesta = "1";
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
		DetaPaquete detaPaquete = (DetaPaquete) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.detaPaquete\r\n"
				+ "SET idProducto = ?, \r\n"
				+ "detaProducto = ? \r\n"
				+ "pcosto = ?, \r\n"
				+ "pventa = ? \r\n"
				+ "cantidad = ?, \r\n"
				+ "idbodega = ? \r\n"
				+ "WHERE IdDetaPaquete = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(2, detaPaquete.getIdProducto());
			ps.setString(3, detaPaquete.getDetaProducto());
			ps.setBigDecimal(4, detaPaquete.getCosto());
			ps.setBigDecimal(5, detaPaquete.getVenta());
			ps.setDouble(6, detaPaquete.getCantidad());
			ps.setDouble(7, detaPaquete.getIdBodega());
			ps.setInt(8, detaPaquete.getIdDetaPaquete());
			

			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			codigoRespuesta = "1";
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
		DetaPaquete detaPaquete = (DetaPaquete) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "DELETE FROM dbo.detaPaquete\r\n"
				+ "WHERE IdDetaPaquete = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, detaPaquete.getIdDetaPaquete());


			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			codigoRespuesta = "1";
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return codigoRespuesta;
	}

	public List<DetaPaquete> getDetallePaquete(Object entity) throws SQLException {
		String paquete = (String) entity;
		List<DetaPaquete> listaDetalle = new ArrayList<DetaPaquete>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		ResultSet rs;
		String query = "SELECT dp.idDetapaquete, dp.idPaquete, dp.idProducto, dp.DetaProducto,\r\n"
				+ "dp.Pcosto, dp.Pventa, dp.Cantidad, dp.IdBodega\r\n"
				+ "FROM dbo.detaPaquete dp\r\n"
				+ "WHERE dp.idPaquete = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete);
			rs = ps.executeQuery();
			if (rs.next()) {
				DetaPaquete detaPaquete = new DetaPaquete();
				detaPaquete.setIdDetaPaquete(rs.getInt("idDetapaquete"));
				detaPaquete.setIdPaquete(rs.getString("idPaquete"));
				detaPaquete.setIdProducto(rs.getString("idProducto"));
				detaPaquete.setDetaProducto(rs.getString("DetaProducto"));
				detaPaquete.setCosto(rs.getBigDecimal("Pcosto"));
				detaPaquete.setVenta(rs.getBigDecimal("Pventa"));
				detaPaquete.setCantidad(rs.getInt("Cantidad"));
				detaPaquete.setIdBodega(rs.getInt("IdBodega"));
				
				listaDetalle.add(detaPaquete);
			}
		} catch (Exception e) {
			listaDetalle = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDetalle;
	}
}
