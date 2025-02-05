package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.dao.PedidosDaoRequest;
import com.erp.variety.model.Bodega;
import com.erp.variety.model.Clientes;
import com.erp.variety.model.Paquete;
import com.erp.variety.util.SqlConn;

public class PedidosJDBC extends AbstractJDBC{

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
		Paquete paquete = new Paquete();
		
		return paquete;
	}

	@Override
	public String save(Object entity) throws SQLException {
		String codigoRespuesta = "0";
		
		return codigoRespuesta;
	}

	@Override
	public String edit(Object entity) throws SQLException {
		PedidosDaoRequest pedido = (PedidosDaoRequest) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Pedido\r\n"
				+ "SET Cancelado = ?,\r\n"
				+ "estado = ?,\r\n"
				+ "tipo = ?,\r\n"
				+ "Numero = ?,\r\n"
				+ "fecha = ?,\r\n"
				+ "fechaEntrega = ?,\r\n"
				+ "IdCliente = ?,\r\n"
				+ "nombreCliente = ?,\r\n"
				+ "IdEmpleado = ?,\r\n"
				+ "Sumas = ?,\r\n"
				+ "Impuesto = ?,\r\n"
				+ "Total = ?\r\n"
				+ "WHERE IdPedido = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pedido.getCancelado());
			ps.setString(2, pedido.getEstado());
			ps.setString(3, pedido.getTipo());
			ps.setString(4, pedido.getNumero());
			ps.setDate(5, (Date) pedido.getFecha());
			ps.setDate(6, (Date) pedido.getFechaEntrega());
			ps.setString(7, pedido.getIdCliente());
			ps.setString(8, pedido.getNombreCliente());
			ps.setInt(9, pedido.getIdEmpleado());
			ps.setBigDecimal(10, pedido.getSumas());
			ps.setBigDecimal(11, pedido.getImpuesto());
			ps.setBigDecimal(12, pedido.getTotal());
			ps.setInt(13, pedido.getIdPedido());
			

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
		PedidosDaoRequest pedido = (PedidosDaoRequest) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "DELETE FROM dbo.Pedido\r\n"
				+ "WHERE IdPedido = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, pedido.getIdPedido());

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
		String correlativo = "";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT count(IdPedido) + 1 as correlativo "
				+ "FROM dbo.Pedido";
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
