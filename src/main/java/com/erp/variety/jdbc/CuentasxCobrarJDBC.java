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
import com.erp.variety.model.Cobros;
import com.erp.variety.model.Movimientos;
import com.erp.variety.model.Paquete;
import com.erp.variety.util.SqlConn;

public class CuentasxCobrarJDBC extends AbstractJDBC {

	@Override
	public List<Paquete> findAll() throws SQLException {
		List<Paquete> listapaquete = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT p.IdPaquete, p.Descripcion, p.pCosto, p.pVenta, p.Saldo, p.fecha_asignacion, p.IdBodega,\r\n"
				+ "p.entregado, p.pagoafecha, p.IdCliente, c.nombreCliente\r\n"
				+ "FROM dbo.Paquete p\r\n"
				+ "LEFT JOIN dbo.Cliente c on c.IdCliente = p.IdCliente\r\n"
				+ "WHERE entregado is not NULL";
				
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
				cliente.setNombreCliente(rs.getString("nombreCliente"));
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
//	public List<Cobros> findAll() throws SQLException {
//		List<Cobros> listaCobros = new ArrayList<>();
//		SqlConn sconn = new SqlConn();
//		Connection conn = sconn.getConnection();
//		Statement st = null;
//		ResultSet rs;
//		String query = "SELECT IdCobro, FechaCobro, MontoPagado, Estado, idCliente \r\n"
//				+ "FROM dbo.cobro";
//
//		try {
//			st = conn.createStatement();
//			rs = st.executeQuery(query);
//			while (rs.next()) {
//				Cobros cobro = new Cobros();
//				cobro.setIdCobro(rs.getInt("IdCobro"));
//				cobro.setFechaCobro(rs.getDate("FechaCobro"));
//				cobro.setEstado(rs.getInt("Estado"));
//				cobro.setMontoPagado(rs.getBigDecimal("MontoPagado"));
//				Clientes cliente = new Clientes();
//				cliente.setIdCliente(rs.getString("idCliente"));
//				cobro.setCliente(cliente);
//				listaCobros.add(cobro);
//			}
//		} catch (Exception e) {
//			listaCobros = null;
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//				st.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return listaCobros;
//	}

	@Override
	public Cobros getRecord(Object entity) throws SQLException {
		Cobros cobro = (Cobros) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		ResultSet rs;
		String query = "SELECT IdCobro, FechaCobro, MontoPagado, Estado, idCliente "
				+ "FROM dbo.cobro "
				+ "WHERE IdCobro = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cobro.getIdCobro());
			rs = ps.executeQuery();
			if (rs.next()) {
				cobro.setIdCobro(rs.getInt("IdCobro"));
				cobro.setFechaCobro(rs.getDate("FechaCobro"));
				cobro.setEstado(rs.getInt("Estado"));
				cobro.setMontoPagado(rs.getBigDecimal("MontoPagado"));
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("idCliente"));
			}
		} catch (Exception e) {
			cobro = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cobro;
	}

	@Override
	public String save(Object entity) throws SQLException {
		Cobros cobro = (Cobros) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.cobro (IdCobro, FechaCobro, MontoPagado, Estado, idCliente) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cobro.getIdCobro());
			ps.setDate(2, (Date) cobro.getFechaCobro());
			ps.setBigDecimal(3, cobro.getMontoPagado());
			ps.setInt(4, cobro.getEstado());
			ps.setString(5, cobro.getCliente().getIdCliente());

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
		Cobros cobro = (Cobros) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, (Date) cobro.getFechaCobro());
			ps.setBigDecimal(2, cobro.getMontoPagado());
			ps.setInt(3, cobro.getEstado());
			ps.setString(4, cobro.getCliente().getIdCliente());
			ps.setInt(5, cobro.getIdCobro());

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

		return correlativo;
	}
	
	public List<Movimientos> getMovimientosByClient(String idCliente) throws SQLException {
		List<Movimientos> listaMovimientos = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		ResultSet rs;
		String query = "SELECT A.*, \r\n"
				+ "(SELECT SUM(saldo) FROM dbo.Paquete WHERE entregado = 1 AND IdCliente = ?) -\r\n"
				+ "(SELECT ISNULL(SUM(MontoPagado),0) FROM dbo.Cobro WHERE IdCliente = ?) AS saldoTotal \r\n"
				+ "FROM (\r\n"
				+ "SELECT Descripcion AS descripcion,\r\n"
				+ "pCosto AS debito,\r\n"
				+ "0 AS credito,\r\n"
				+ "Saldo AS saldo,\r\n"
				+ "c.IdCliente AS idCliente,\r\n"
				+ "c.nombreCliente,\r\n"
				+ "p.fecha_asignacion AS fechaMovimiento\r\n"
				+ "FROM dbo.Paquete p\r\n"
				+ "INNER JOIN Cliente c on c.IdCliente = p.idCliente\r\n"
				+ "WHERE entregado = 1\r\n"
				+ "UNION ALL\r\n"
				+ "SELECT 'ABONO' AS descripcion,\r\n"
				+ "0 AS debito,\r\n"
				+ "MontoPagado AS credito,\r\n"
				+ "0 AS saldo,\r\n"
				+ "c.IdCliente AS idCliente,\r\n"
				+ "c.nombreCliente,\r\n"
				+ "co.FechaCobro AS fechaMovimiento\r\n"
				+ "FROM dbo.Cobro co\r\n"
				+ "INNER JOIN Cliente c on c.IdCliente = co.idCliente\r\n"
				+ ") A \r\n"
				+ "WHERE A.IdCliente = ?\r\n"
				+ "GROUP BY A.descripcion, A.debito, A.credito, A.saldo, A.idCliente, A.nombreCliente, A.fechaMovimiento \r\n"
				+ "ORDER BY A.fechaMovimiento DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idCliente);
			ps.setString(2, idCliente);
			ps.setString(3, idCliente);
			rs = ps.executeQuery();
			while (rs.next()) {
				Movimientos movimientos = new Movimientos();
				movimientos.setDebito(rs.getBigDecimal("debito"));
				movimientos.setCredito(rs.getBigDecimal("credito"));
				movimientos.setDescripcion(rs.getString("descripcion"));
				movimientos.setSaldo(rs.getBigDecimal("saldo"));
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("idCliente"));
				cliente.setNombreCliente(rs.getString("nombreCliente"));
				movimientos.setFechaMovimiento(rs.getDate("fechaMovimiento"));
				movimientos.setSaldoTotal(rs.getBigDecimal("saldoTotal"));
				movimientos.setCliente(cliente);
				listaMovimientos.add(movimientos);
			}
		} catch (Exception e) {
			listaMovimientos = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaMovimientos;
	}

}
