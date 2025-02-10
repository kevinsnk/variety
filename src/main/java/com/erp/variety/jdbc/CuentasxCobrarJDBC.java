package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Clientes;
import com.erp.variety.model.Cobros;
import com.erp.variety.util.SqlConn;

public class CuentasxCobrarJDBC extends AbstractJDBC {

	@Override
	public List<Cobros> findAll() throws SQLException {
		List<Cobros> listaCobros = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdCobro, FechaCobro, MontoPagado, Estado, idCliente \r\n"
				+ "FROM dbo.cobro";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Cobros cobro = new Cobros();
				cobro.setIdCobro(rs.getInt("IdCobro"));
				cobro.setFechaCobro(rs.getDate("FechaCobro"));
				cobro.setEstado(rs.getInt("Estado"));
				cobro.setMontoPagado(rs.getBigDecimal("MontoPagado"));
				Clientes cliente = new Clientes();
				cliente.setIdCliente(rs.getString("idCliente"));
				cobro.setCliente(cliente);
				listaCobros.add(cobro);
			}
		} catch (Exception e) {
			listaCobros = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCobros;
	}

	@Override
	public Cobros getRecord(Object entity) throws SQLException {
		Cobros cobro = (Cobros) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
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
				st.close();
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

}
