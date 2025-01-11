package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Empleado;
import com.erp.variety.util.SqlConn;

public class EmpleadoJDBC extends AbstractJDBC{


	@Override
	public List<Empleado> findAll() throws SQLException {
		List<Empleado> listaempleado = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Empleado";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
				empleado.setNombreEmpleado(rs.getString("NombreEmpleado"));
				empleado.setApellidoEmpleado(rs.getString("ApellidoEmpleado"));
				empleado.setDireccionEmpleado(rs.getString("DireccionEmpleado"));
				empleado.setTelefonoEmpleado(rs.getString("TelefonoEmpleado"));
				empleado.setCelularEmpleado(rs.getString("CelularEmpleado"));
				empleado.setEmailEmpleado(rs.getString("emailEmpleado"));
				empleado.setActivo(rs.getInt("Activo"));
				empleado.setTipoEmpleado(rs.getString("TipoEmpleado"));
				
				listaempleado.add(empleado);
			}
		} catch (Exception e) {
			listaempleado = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaempleado;
	}

	@Override
	public Empleado getRecord(Object entity) throws SQLException {
		Empleado empleado = (Empleado) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT * FROM dbo.Empleado";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {

				empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
				empleado.setNombreEmpleado(rs.getString("NombreEmpleado"));
				empleado.setApellidoEmpleado(rs.getString("ApellidoEmpleado"));
				empleado.setDireccionEmpleado(rs.getString("DireccionEmpleado"));
				empleado.setTelefonoEmpleado(rs.getString("TelefonoEmpleado"));
				empleado.setCelularEmpleado(rs.getString("CelularEmpleado"));
				empleado.setEmailEmpleado(rs.getString("emailEmpleado"));
				empleado.setActivo(rs.getInt("Activo"));
				empleado.setTipoEmpleado(rs.getString("TipoEmpleado"));
			}
		} catch (Exception e) {
			empleado = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empleado;
	}

	@Override
	public String save(Object entity) throws SQLException {
		Empleado empleado = (Empleado) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "INSERT INTO dbo.Empleado(NombreEmpleado, ApellidoEmpleado, "
				+ "direccionEmpleado, TelefonoEmpleado, CelularEmpleado, "
				+ "emailEmpleado, Activo, TipoEmpleado) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, empleado.getNombreEmpleado());
			ps.setString(2, empleado.getApellidoEmpleado());
			ps.setString(3, empleado.getDireccionEmpleado());
			ps.setString(4, empleado.getTelefonoEmpleado());
			ps.setString(5, empleado.getCelularEmpleado());
			ps.setString(6, empleado.getEmailEmpleado());
			ps.setInt(7, empleado.getActivo());
			ps.setString(8, empleado.getTipoEmpleado());
			
			

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			codigoRespuesta = String.valueOf(e.getErrorCode());
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
		Empleado empleado = (Empleado) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "UPDATE dbo.Empleado\r\n"
				+ "SET NombreEmpleado = ?, \r\n"
				+ "ApellidoEmpleado= ? , \r\n"
				+ "direccionEmpleado= ? , \r\n"
				+ "TelefonoEmpleado= ? , \r\n"
				+ "CelularEmpleado= ? , \r\n"
				+ "emailEmpleado= ? , \r\n"
				+ "Activo= ? , \r\n"
				+ "TipoEmpleado= ? \r\n"
				+ "WHERE IdEmpleado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, empleado.getNombreEmpleado());
			ps.setString(2, empleado.getApellidoEmpleado());
			ps.setString(3, empleado.getDireccionEmpleado());
			ps.setString(4, empleado.getTelefonoEmpleado());
			ps.setString(5, empleado.getCelularEmpleado());
			ps.setString(6, empleado.getEmailEmpleado());
			ps.setInt(7, empleado.getActivo());
			ps.setString(8, empleado.getTipoEmpleado());
			ps.setInt(9, empleado.getIdEmpleado());

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			codigoRespuesta = String.valueOf(e.getErrorCode());
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
		Empleado empleado = (Empleado) entity;
		String codigoRespuesta = "0";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String query = "DELETE FROM dbo.Empleado\r\n"
				+ "WHERE IdEmpleado = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, empleado.getIdEmpleado());


			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			codigoRespuesta = String.valueOf(e.getErrorCode());
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
		String query = "SELECT count(IdEmpleado) + 1 as correlativo "
				+ "FROM dbo.Empleado";
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
