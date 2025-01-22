package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.dao.ClientesDaoRequest;
import com.erp.variety.dao.ProductoDaoRequest;
import com.erp.variety.model.Clientes;
import com.erp.variety.model.Producto;
import com.erp.variety.util.SqlConn;

public class ProductoJDBC {


	@Override
	public List<Producto> findAll() throws SQLException {
		List<Producto> listaProducto = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdProducto, Descripcion, DescriPrint, Grupo, Tipo, UniCompra, ValCompra,\r\n"
				+ " UniVenta, UniInvent, ValInvent, Serie,Lote "
				+ "FROM dbo.Producto";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getString("idProducto"));
				producto.setDescripcion(rs.getString("Descripcion"));
				producto.setDescriPrint(rs.getString("DescriPrint"));
				producto.setGrupo(rs.getInt("Grupo"));
				producto.setTipo(rs.getInt("Tipo"));
				producto.setUniCompra(rs.getString("UniCompra"));
				producto.setValCompra(rs.getInt("ValCompra"));
				producto.setUniVenta(rs.getString("UniVenta"));
				producto.setUniInvent(rs.getString("UniInvent"));
				producto.setValInvent(rs.getInt("ValInvent"));
				producto.setSerie(rs.getString("Serie"));
				producto.setLote(rs.getString("Lote"));
				
				
				listaProducto.add(producto);
			}
		} catch (Exception e) {
			listaProducto = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaProducto;
	}

	@Override
	public Producto getRecord(Object entity) throws SQLException {
		Producto producto = (Producto) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdProducto, Descripcion, DescriPrint, Grupo, Tipo, UniCompra, ValCompra,\r\n"
				+ " UniVenta, UniInvent, ValInvent, Serie,Lote "
				+ "FROM dbo.Producto";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				producto.setIdProducto(rs.getString("idProducto"));
				producto.setDescripcion(rs.getString("Descripcion"));
				producto.setDescriPrint(rs.getString("DescriPrint"));
				producto.setGrupo(rs.getInt("Grupo"));
				producto.setTipo(rs.getInt("Tipo"));
				producto.setUniCompra(rs.getString("UniCompra"));
				producto.setValCompra(rs.getInt("ValCompra"));
				producto.setUniVenta(rs.getString("UniVenta"));
				producto.setUniInvent(rs.getString("UniInvent"));
				producto.setValInvent(rs.getInt("ValInvent"));
				producto.setSerie(rs.getString("Serie"));
				producto.setLote(rs.getString("Lote"));
			}
		} catch (Exception e) {
			producto = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return producto;
	}

	@Override
	public String save(Object entity) throws SQLException {
		ProductoDaoRequest producto = (ProductoDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "INSERT INTO dbo.Producto (IdProducto, Descripcion, DescriPrint, Grupo, Tipo,\r\n"
				+ "UniCompra, ValCompra, UniVenta, UniInvent, ValInvent, Serie, Lote)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getIdProducto());
			ps.setString(2, producto.getDescripcion());
			ps.setString(3, producto.getDescriPrint());
			ps.setInt(4, producto.getGrupo());
			ps.setInt(5, producto.getTipo());
			ps.setString(6, producto.getUniCompra());
			ps.setInt(7, producto.getValCompra());
			ps.setString(8, producto.getUniVenta());
			ps.setInt(9, producto.getValVenta());
			ps.setString(10, producto.getUniInvent());
			ps.setInt(11, producto.getValInvent());
			ps.setString(12, producto.getSerie());
			ps.setString(13, producto.getLote());


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
		ProductoDaoRequest producto = (ProductoDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "UPDATE dbo.Producto\r\n"
				+ "SET Descripcion = ? \r\n"
				+ "DescriPrint= ? \r\n"
				+ "Grupo= ?\r\n"
				+ "Tipo= ? \r\n"
				+ "UniCompra= ? \r\n"
				+ "ValCompra= ? \r\n"
				+ "UniVenta= ? \r\n"
				+ "UniInvent= ? \r\n"
				+ "ValInvent= ? \r\n"
				+ "Serie= ? \r\n"
				+ "Lote= ? \r\n"
				+ "WHERE IdProducto = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getIdProducto());
			ps.setString(2, producto.getDescripcion());
			ps.setString(3, producto.getDescriPrint());
			ps.setInt(4, producto.getGrupo());
			ps.setInt(5, producto.getTipo());
			ps.setString(6, producto.getUniCompra());
			ps.setInt(7, producto.getValCompra());
			ps.setString(8, producto.getUniVenta());
			ps.setInt(9, producto.getValVenta());
			ps.setString(10, producto.getUniInvent());
			ps.setInt(11, producto.getValInvent());
			ps.setString(12, producto.getSerie());
			ps.setString(13, producto.getLote());

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
		ProductoDaoRequest producto = (ProductoDaoRequest) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "DELETE FROM dbo.Producto\r\n"
				+ "WHERE IdProducto = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getIdCliente());


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
		String query = "SELECT count(idproducto) + 1 as correlativo "
				+ "FROM dbo.Producto";
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
