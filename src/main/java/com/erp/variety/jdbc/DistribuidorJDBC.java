package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Distribuidor;
import com.erp.variety.util.SqlConn;

public class DistribuidorJDBC extends AbstractJDBC{

	@Override
	public List<Distribuidor> findAll() throws SQLException {
		List<Distribuidor> listaDistribuidor = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT Iddistribuidor, Nombre, Apellido, Direccion, Departamento,\r\n"
				+ "Municipio, Sexo, Dui, Telefono, Celular, whatsapp, zona, Email"
				+ "FROM dbo.Distribuidor";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Distribuidor distribuidor = new Distribuidor();
				distribuidor.setIdDistribuidor(rs.getString("IdDistribuidor"));
				distribuidor.setNombre(rs.getString("Nombre"));
				distribuidor.setApellido(rs.getString("Apellido"));
				distribuidor.setDireccion(rs.getString("Direccion"));
				distribuidor.setDepartamento(rs.getString("Departamento"));
				distribuidor.setMunicipio(rs.getString("Municipio"));
				distribuidor.setSexo(rs.getString("Sexo"));
				distribuidor.setDui(rs.getString("Dui"));
				distribuidor.setTelefono(rs.getString("Telefono"));
				distribuidor.setCelular(rs.getString("Celular"));
				distribuidor.setWhatsapp(rs.getString("whatsapp"));
				distribuidor.setZona(rs.getString("Zona"));
				distribuidor.setEmail(rs.getString("Email"));
				
				
				listaDistribuidor.add(distribuidor);
			}
		} catch (Exception e) {
			listaDistribuidor = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaDistribuidor;
	}

	@Override
	public Distribuidor getRecord(Object entity) throws SQLException {
		Distribuidor distribuidor = (Distribuidor) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT Iddistribuidor, Nombre, Apellido, Direccion, Departamento,\r\n"
				+ "Municipio, Sexo, Dui, Telefono, Celular, whatsapp, zona, Email"
				+ "FROM dbo.Distribuidor";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				distribuidor.setIdDistribuidor(rs.getString("IdDistribuidor"));
				distribuidor.setNombre(rs.getString("Nombre"));
				distribuidor.setApellido(rs.getString("Apellido"));
				distribuidor.setDireccion(rs.getString("Direccion"));
				distribuidor.setDepartamento(rs.getString("Departamento"));
				distribuidor.setMunicipio(rs.getString("Municipio"));
				distribuidor.setSexo(rs.getString("Sexo"));
				distribuidor.setDui(rs.getString("Dui"));
				distribuidor.setTelefono(rs.getString("Telefono"));
				distribuidor.setCelular(rs.getString("Celular"));
				distribuidor.setWhatsapp(rs.getString("whatsapp"));
				distribuidor.setZona(rs.getString("Zona"));
				distribuidor.setEmail(rs.getString("Email"));
			}
		} catch (Exception e) {
			distribuidor = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return distribuidor;
	}

	@Override
	public String save(Object entity) throws SQLException {
		Distribuidor distribuidor = (Distribuidor) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.Distribuidor(IdDistribuidor,Nombre,Apellido,Direccion,\r\n"
				+ "Departamento,Municipio,Sexo,Dui,Telefono,Celular,whatsapp,zona)\r\n"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, distribuidor.getIdDistribuidor());
			ps.setString(2, distribuidor.getNombre());
			ps.setString(3, distribuidor.getApellido());
			ps.setString(4, distribuidor.getDireccion());
			ps.setString(5, distribuidor.getDepartamento());
			ps.setString(6, distribuidor.getMunicipio());
			ps.setString(7, distribuidor.getSexo());
			ps.setString(8, distribuidor.getDui());
			ps.setString(9, distribuidor.getTelefono());
			ps.setString(10, distribuidor.getCelular());
			ps.setString(11, distribuidor.getWhatsapp());
			ps.setString(12, distribuidor.getZona());
			ps.setString(13, distribuidor.getEmail());
		

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
		Distribuidor distribuidor = (Distribuidor) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Distribuidor\r\n"
				+ "SET Nombre= ? \r\n"
				+ "Apellido= ? \r\n"
				+ "Direccion= ?\r\n"
				+ "Departamento= ? \r\n"
				+ "Municipio= ? \r\n"
				+ "Sexo= ? \r\n"
				+ "Dui= ? \r\n"
				+ "Telefono= ? \r\n"
				+ "Celular= ? \r\n"
				+ "whatsapp= ? \r\n"
				+ "Zona= ? \r\n"
				+ "email= ? \r\n"
			    + "WHERE IdDistribuidor = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, distribuidor.getIdDistribuidor());
			ps.setString(2, distribuidor.getNombre());
			ps.setString(3, distribuidor.getApellido());
			ps.setString(4, distribuidor.getDireccion());
			ps.setString(5, distribuidor.getDepartamento());
			ps.setString(6, distribuidor.getMunicipio());
			ps.setString(7, distribuidor.getSexo());
			ps.setString(8, distribuidor.getDui());
			ps.setString(9, distribuidor.getTelefono());
			ps.setString(10, distribuidor.getCelular());
			ps.setString(11, distribuidor.getWhatsapp());
			ps.setString(12, distribuidor.getZona());
			ps.setString(13, distribuidor.getEmail());

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
		Distribuidor distribuidor = (Distribuidor) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "DELETE FROM dbo.Distribuidor\r\n"
				+ "WHERE IdDistribuidor = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, distribuidor.getIdDistribuidor());


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
	
}
