package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Unidad;
import com.erp.variety.util.SqlConn;

public class UnidadJDBC extends AbstractJDBC{

	@Override
	public List<Unidad> findAll() throws SQLException {
		List<Unidad> listaUnidades = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdUnidad, descripcion, unidad_medida "
				+ "FROM dbo.Unidad";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Unidad unidad = new Unidad();
				unidad.setIdUnidad(rs.getInt("IdUnidad"));
				unidad.setDescripcion(rs.getString("descripcion"));
				unidad.setUnidadMedida(rs.getInt("unidad_medida"));
				
				listaUnidades.add(unidad);
			}
		} catch (Exception e) {
			listaUnidades = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaUnidades;
	}

	@Override
	public Object getRecord(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCorrelativo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Object entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
