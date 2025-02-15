package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Bodega;
import com.erp.variety.model.Clientes;
import com.erp.variety.model.Paquete;
import com.erp.variety.util.SqlConn;

public class PaqueteJDBC extends AbstractJDBC{

	@Override
	public List<Paquete> findAll() throws SQLException {
		List<Paquete> listapaquete = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT p.IdPaquete, p.Descripcion, p.pCosto, p.pVenta, p.Saldo, p.fecha_asignacion, p.IdBodega,\r\n"
				+ "p.entregado, p.pagoafecha, p.IdCliente\r\n"
				+ "FROM dbo.Paquete p \r\n"
				+ "WHERE entregado is NULL";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Paquete paquete = new Paquete();
				paquete.setIdPaquete(rs.getString("IdPaquete"));
				paquete.setDescripcion(rs.getString("Descripcion"));
				paquete.setPCosto(rs.getBigDecimal("pCosto"));
				paquete.setPVenta(rs.getBigDecimal("pVenta"));
				paquete.setSaldo(rs.getBigDecimal("Saldo"));
				paquete.setFechaAsignacion(rs.getDate("fecha_asignacion"));
				Bodega bodega = new Bodega();
				bodega.setIdBodega(rs.getString("IdBodega"));
				paquete.setIdBodega(bodega);
				paquete.setEntregado(rs.getString("entregado"));
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("IdCliente"));
				paquete.setCliente(cliente);
				paquete.setPagoaFecha(rs.getDate("pagoafecha"));
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
				paquete.setPCosto(rs.getBigDecimal("pCosto"));
				paquete.setPVenta(rs.getBigDecimal("pVenta"));
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
	
	public List<Paquete> getPaquetesXAsignar() throws SQLException {
		List<Paquete> listapaquete = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT p.IdPaquete, p.Descripcion, p.pCosto, p.pVenta, p.Saldo, p.fecha_asignacion, p.IdBodega,\r\n"
				+ "p.entregado, p.pagoafecha, p.IdCliente\r\n"
				+ "FROM dbo.Paquete p \r\n"
				+ "WHERE IdBodega is NULL";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Paquete paquete = new Paquete();
				paquete.setIdPaquete(rs.getString("IdPaquete"));
				paquete.setDescripcion(rs.getString("Descripcion"));
				paquete.setPCosto(rs.getBigDecimal("pCosto"));
				paquete.setPVenta(rs.getBigDecimal("pVenta"));
				paquete.setSaldo(rs.getBigDecimal("Saldo"));
				paquete.setFechaAsignacion(rs.getDate("fecha_asignacion"));
				Bodega bodega = new Bodega();
				bodega.setIdBodega(rs.getString("IdBodega"));
				paquete.setIdBodega(bodega);
				paquete.setEntregado(rs.getString("entregado"));
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("IdCliente"));
				paquete.setCliente(cliente);
				paquete.setPagoaFecha(rs.getDate("pagoafecha"));
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
	public String save(Object entity) throws SQLException {
		Paquete paquete = (Paquete) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.Paquete (IdPaquete, Descripcion, pCosto, pVenta) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete.getIdPaquete());
			ps.setString(2, paquete.getDescripcion());
			ps.setBigDecimal(3, paquete.getPCosto());
			ps.setBigDecimal(4, paquete.getPVenta());
			

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
		Paquete paquete = (Paquete) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Paquete\r\n"
				+ "SET Descripcion = ?, \r\n"
				+ "pCosto= ?, \r\n"
				+ "pVenta= ?, \r\n"
				+ "Saldo= ?, \r\n"
				+ "fecha_asignacion= ?, \r\n"
				+ "IdBodega= ?, \r\n"
				+ "entregado= ?, \r\n"
				//+ "pagoafecha= ?, \r\n"
				+ "IdCliente= ? \r\n"
				+ "WHERE IdPaquete = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, paquete.getDescripcion());
			ps.setBigDecimal(2, paquete.getPCosto());
			ps.setBigDecimal(3, paquete.getPVenta());
			ps.setBigDecimal(4, paquete.getSaldo());
			ps.setDate(5, new Date(paquete.getFechaAsignacion().getTime()));
			ps.setString(6, paquete.getIdBodega().getIdBodega());
			ps.setString(7, paquete.getEntregado());
//			if(paquete.getPagoaFecha() != null) {
//				ps.setDate(8, new Date(paquete.getPagoaFecha().getTime()));
//			}else {
//				ps.setDate(8, null);
//			}
			ps.setString(8, paquete.getCliente().getIdCliente());
			ps.setString(9, paquete.getIdPaquete());
			

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
		String codigoRespuesta = "0";
		
		
		return codigoRespuesta;
	}

	@Override
	public String getCorrelativo() throws SQLException {
		String correlativo = "";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT MAX(CAST(IdPaquete AS INT)) + 1 as correlativo "
				+ "FROM dbo.Paquete";
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
	
	public String asignarBodega(String idBodega, String idPaquetes) throws SQLException {
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Paquete\r\n"
				+ "SET IdBodega= ? \r\n"
				+ "WHERE IdPaquete IN(?)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idBodega);
			ps.setString(2, idPaquetes);
			
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
	
	public List<Paquete> getPaquetesXCliente(String idCliente) throws SQLException {
		List<Paquete> listapaquete = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT p.IdPaquete, p.Descripcion, p.pCosto, p.pVenta, p.Saldo, p.fecha_asignacion, p.IdBodega,\r\n"
				+ "p.entregado, p.pagoafecha, p.IdCliente\r\n"
				+ "FROM dbo.Paquete p \r\n"
				+ "WHERE IdCliente = ? \r\n"
				+ "ORDER BY IdPaquete ASC";
				
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idCliente);
			rs = ps.executeQuery();
			while (rs.next()) {
				Paquete paquete = new Paquete();
				paquete.setIdPaquete(rs.getString("IdPaquete"));
				paquete.setDescripcion(rs.getString("Descripcion"));
				paquete.setPCosto(rs.getBigDecimal("pCosto"));
				paquete.setPVenta(rs.getBigDecimal("pVenta"));
				paquete.setSaldo(rs.getBigDecimal("Saldo"));
				paquete.setFechaAsignacion(rs.getDate("fecha_asignacion"));
				Bodega bodega = new Bodega();
				bodega.setIdBodega(rs.getString("IdBodega"));
				paquete.setIdBodega(bodega);
				paquete.setEntregado(rs.getString("entregado"));
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("IdCliente"));
				paquete.setCliente(cliente);
				paquete.setPagoaFecha(rs.getDate("pagoafecha"));
				listapaquete.add(paquete);
			}
		} catch (Exception e) {
			listapaquete = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listapaquete;
	}
}
