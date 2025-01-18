package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.dao.ClientesDaoRequest;
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
		String query = "SELECT IdCliente, nombreCliente, nombreComercial, grupoCliente,\r\n"
				+ "DireccionCliente, PaisCliente, DepartamentoCliente, MunicipioCliente, \r\n"
				+ "TelefonoCliente, CelularCliente, EmailCliente, NrcCliente, NitCliente, DuiCliente,\r\n"
				+ "GiroCliente, ContactoCliente, SitiowebCliente, WhatsappCliente, Latitud, Longitud, \r\n"
				+ "CtaContableCliente, IdEmpleado, Activo "
				+ "FROM dbo.Cliente";
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
		String query = "SELECT IdCliente, nombreCliente, nombreComercial, grupoCliente,\r\n"
				+ "DireccionCliente, PaisCliente, DepartamentoCliente, MunicipioCliente, \r\n"
				+ "TelefonoCliente, CelularCliente, EmailCliente, NrcCliente, NitCliente, DuiCliente,\r\n"
				+ "GiroCliente, ContactoCliente, SitiowebCliente, WhatsappCliente, Latitud, Longitud, \r\n"
				+ "CtaContableCliente, IdEmpleado, Activo "
				+ "FROM dbo.Cliente";
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
	public String save(Object entity) throws SQLException {
		ClientesDaoRequest clientes = (ClientesDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "INSERT INTO dbo.Cliente(IdCliente,nombreCliente, nombreComercial, grupoCliente,\r\n"
				+ "DireccionCliente, PaisCliente, DepartamentoCliente, MunicipioCliente, \r\n"
				+ "TelefonoCliente, CelularCliente, EmailCliente, NrcCliente, NitCliente, DuiCliente,\r\n"
				+ "GiroCliente, ContactoCliente, SitiowebCliente, WhatsappCliente, Latitud, Longitud, \r\n"
				+ "CtaContableCliente, IdEmpleado, Activo)\r\n"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, clientes.getIdCliente());
			ps.setString(2, clientes.getNombreCliente());
			ps.setString(3, clientes.getNombreComercial());
			ps.setInt(4, clientes.getGrupoCliente());
			ps.setString(5, clientes.getDireccionCliente());
			ps.setString(6, clientes.getPaisCliente());
			ps.setString(7, clientes.getDepartamentoCliente());
			ps.setString(8, clientes.getMunicipioCliente());
			ps.setString(9, clientes.getTelefonoCliente());
			ps.setString(10, clientes.getCelularCliente());
			ps.setString(11, clientes.getEmailCliente());
			ps.setString(12, clientes.getNrcCliente());
			ps.setString(13, clientes.getNitCliente());
			ps.setString(14, clientes.getDuiCliente());
			ps.setString(15, clientes.getGiroCliente());
			ps.setString(16, clientes.getContactoCliente());
			ps.setString(17, clientes.getSitioWebCliente());
			ps.setString(18, clientes.getWhatsappCliente());
			ps.setString(19, clientes.getLatitud());
			ps.setString(20, clientes.getLongitud());
			ps.setString(21, clientes.getCtaContableCliente());
			ps.setInt(22, clientes.getIdEmpleado());
			ps.setInt(23, clientes.getActivo());

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
		ClientesDaoRequest clientes = (ClientesDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "UPDATE dbo.Cliente\r\n"
				+ "SET nombreCliente= ? \r\n"
				+ "nombreComercial= ? \r\n"
				+ "grupoCliente= ?\r\n"
				+ "DireccionCliente= ? \r\n"
				+ "PaisCliente= ? \r\n"
				+ "DepartamentoCliente= ? \r\n"
				+ "MunicipioCliente= ? \r\n"
				+ "TelefonoCliente= ? \r\n"
				+ "CelularCliente= ? \r\n"
				+ "EmailCliente= ? \r\n"
				+ "NrcCliente= ? \r\n"
				+ "NitCliente= ? \r\n"
				+ "DuiCliente= ?\r\n"
				+ "GiroCliente= ? \r\n"
				+ "ContactoCliente= ? \r\n"
				+ "SitiowebCliente= ? \r\n"
				+ "WhatsappCliente= ? \r\n"
				+ "Latitud= ? \r\n"
				+ "Longitud= ? \r\n"
				+ "CtaContableCliente= ? \r\n"
				+ "IdEmpleado= ? \r\n"
				+ "Activo = ?\r\n"
				+ "WHERE IdCliente = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, clientes.getNombreCliente());
			ps.setString(2, clientes.getNombreComercial());
			ps.setInt(3, clientes.getGrupoCliente());
			ps.setString(4, clientes.getDireccionCliente());
			ps.setString(5, clientes.getPaisCliente());
			ps.setString(6, clientes.getDepartamentoCliente());
			ps.setString(7, clientes.getMunicipioCliente());
			ps.setString(8, clientes.getTelefonoCliente());
			ps.setString(9, clientes.getCelularCliente());
			ps.setString(10, clientes.getEmailCliente());
			ps.setString(11, clientes.getNrcCliente());
			ps.setString(12, clientes.getNitCliente());
			ps.setString(13, clientes.getDuiCliente());
			ps.setString(14, clientes.getGiroCliente());
			ps.setString(15, clientes.getContactoCliente());
			ps.setString(16, clientes.getSitioWebCliente());
			ps.setString(17, clientes.getWhatsappCliente());
			ps.setString(18, clientes.getLatitud());
			ps.setString(19, clientes.getLongitud());
			ps.setString(20, clientes.getCtaContableCliente());
			ps.setInt(21, clientes.getIdEmpleado());
			ps.setInt(22, clientes.getActivo());
			ps.setString(23, clientes.getIdCliente());

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
		ClientesDaoRequest cliente = (ClientesDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "DELETE FROM dbo.Cliente\r\n"
				+ "WHERE IdCliente = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cliente.getIdCliente());


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
		String query = "SELECT count(idcliente) + 1 as correlativo "
				+ "FROM dbo.Cliente";
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
