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
import com.erp.variety.model.Clientes;
import com.erp.variety.model.Empleado;
import com.erp.variety.model.Pedidos;
import com.erp.variety.util.SqlConn;

public class PedidosJDBC extends AbstractJDBC{

	@Override
	public List<Pedidos> findAll() throws SQLException {
		List<Pedidos> listaPedidos = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT p.*, c.nombreCliente as nombreClient, e.NombreEmpleado, e.ApellidoEmpleado \r\n"
				+ "FROM dbo.Pedido p\r\n"
				+ "INNER JOIN dbo.Cliente c on c.IdCliente = p.IdCliente\r\n"
				+ "INNER JOIN dbo.Empleado e on e.IdEmpleado = p.IdEmpleado";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Pedidos pedidos = new Pedidos();
				Clientes clientes = new Clientes();
				Empleado empleado = new Empleado();
				pedidos.setIdPedido(rs.getInt("IdPedido"));
				pedidos.setCancelado(rs.getString("Cancelado"));
				pedidos.setEstado(rs.getString("Estado"));
				pedidos.setTipo(rs.getString("tipo"));
				pedidos.setNumero(rs.getString("Numero"));
				pedidos.setFecha(rs.getDate("fecha"));
				pedidos.setFechaEntrega(rs.getDate("fechaEntrega"));
				clientes.setIdCliente(rs.getString("IdCliente"));
				clientes.setNombreCliente(rs.getString("nombreClient"));
				pedidos.setCliente(clientes);
				empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
				empleado.setNombreEmpleado(rs.getString("NombreEmpleado"));
				empleado.setApellidoEmpleado(rs.getString("ApellidoEmpleado"));
				pedidos.setEmpleado(empleado);
				pedidos.setSumas(rs.getBigDecimal("Sumas"));
				pedidos.setImpuesto(rs.getBigDecimal("Impuesto"));
				pedidos.setTotal(rs.getBigDecimal("Total"));
				listaPedidos.add(pedidos);
			}
		} catch (Exception e) {
			listaPedidos = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaPedidos;
	}

	@Override
	public Pedidos getRecord(Object entity) throws SQLException {
		Integer idPedidos = (Integer) entity;
		Pedidos pedidos = new Pedidos();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Pedido WHERE IdPedidos = ?";
		try {
			st = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idPedidos);
			rs = ps.executeQuery(query);
			if (rs.next()) {
				Clientes clientes = new Clientes();
				Empleado empleado = new Empleado();
				pedidos.setIdPedido(rs.getInt("IdPedido"));
				pedidos.setCancelado(rs.getString("Cancelado"));
				pedidos.setEstado(rs.getString("Estado"));
				pedidos.setTipo(rs.getString("tipo"));
				pedidos.setNumero(rs.getString("Numero"));
				pedidos.setFecha(rs.getDate("fecha"));
				pedidos.setFechaEntrega(rs.getDate("fechaEntrega"));
				clientes.setIdCliente(rs.getString("IdCliente"));
				clientes.setNombreCliente(rs.getString("nombreCliente"));
				pedidos.setCliente(clientes);
				empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
				pedidos.setEmpleado(empleado);
				pedidos.setSumas(rs.getBigDecimal("Sumas"));
				pedidos.setImpuesto(rs.getBigDecimal("Impuesto"));
				pedidos.setTotal(rs.getBigDecimal("Total"));
			}
		} catch (Exception e) {
			pedidos = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pedidos;
	}

	@Override
	public String save(Object entity) throws SQLException {
		PedidosDaoRequest pedido = (PedidosDaoRequest) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.Pedido(\r\n"
				+ "Cancelado, estado, tipo, Numero, fecha, fechaEntrega, \r\n"
				+ "IdCliente, nombreCliente, IdEmpleado, Sumas, Impuesto, Total) \r\n"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.setInt(1, pedido.getIdPedido());
			ps.setString(1, pedido.getCancelado());
			ps.setString(2, pedido.getEstado());
			ps.setString(3, pedido.getTipo());
			ps.setString(4, pedido.getNumero());
			ps.setDate(5, pedido.getFecha());
			ps.setDate(6, pedido.getFechaEntrega());
			ps.setString(7, pedido.getIdCliente());
			ps.setString(8, pedido.getNombreCliente());
			ps.setInt(9, pedido.getIdEmpleado());
			ps.setBigDecimal(10, pedido.getSumas());
			ps.setBigDecimal(11, pedido.getImpuesto());
			ps.setBigDecimal(12, pedido.getTotal());

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
