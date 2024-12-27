package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Clientes;
import com.erp.variety.util.SqlConn;

public class ClientesJDBC extends AbstractJDBC{

	@Override
	public List<Clientes> findAll() throws SQLException {
		List<Clientes> listaClientes = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdCliente, nombreCliente FROM dbo.Cliente";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("idCliente"));
				cliente.setNombreCliente(rs.getString("nombreCliente"));
				
				listaClientes.add(cliente);
			}
		} catch (Exception e) {
			listaClientes = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaClientes;
	}

	@Override
	public Clientes getRecord(Object entity) throws SQLException {
		Clientes cliente = (Clientes) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT nombreCliente FROM dbo.Cliente";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				cliente.setIdCliente(rs.getString("idCliente"));
				cliente.setNombreCliente(rs.getString("nombreCliente"));
			}
		} catch (Exception e) {
			cliente = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cliente;
	}

	@Override
	public void save(Object entity) throws SQLException {
		Clientes clientes = (Clientes) entity;
		
	}

	@Override
	public void edit(Object entity) throws SQLException {
		Clientes clientes = (Clientes) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.setString(1, clientes.getToken());


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
		Clientes clientes = (Clientes) entity;
		
	}

}
