package com.erp.variety.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.erp.variety.model.Productos;
import com.erp.variety.util.SqlConn;

public class ProductosJDBC extends AbstractJDBC{

	@Override
	public List<Productos> findAll() throws SQLException {
		List<Productos> listaProductos = new ArrayList<>();
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT IdProducto, Descripcion, DescriPrint, Grupo, Tipo, UniCompra, ValCompra,\r\n"
				+ "UniVenta, valVenta, UniInvent, ValInvent, Serie,Lote \r\n"
				+ "FROM dbo.Producto";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Productos producto = new Productos();
				producto.setIdProducto(rs.getString("idProducto"));
				producto.setDescripcion(rs.getString("Descripcion"));
				producto.setDescripPrint(rs.getString("DescriPrint"));
				producto.setGrupo(rs.getInt("Grupo"));
				producto.setTipo(rs.getInt("Tipo"));
				producto.setUniCompra(rs.getString("UniCompra"));
				producto.setValCompra(rs.getBigDecimal("ValCompra"));
				producto.setUniVenta(rs.getString("UniVenta"));
				producto.setValVenta(rs.getBigDecimal("valVenta"));
				producto.setUniInvent(rs.getString("UniInvent"));
				producto.setValInvent(rs.getBigDecimal("ValInvent"));
				producto.setSerie(rs.getString("Serie"));
				producto.setLote(rs.getString("Lote"));
				
				listaProductos.add(producto);
			}
		} catch (Exception e) {
			listaProductos = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaProductos;
	}

	@Override
	public Productos getRecord(Object entity) throws SQLException {
		Productos producto = (Productos) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		ResultSet rs;
		String query = "SELECT * FROM Producto where idProducto = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getIdProducto());
			rs = ps.executeQuery(query);
			if (rs.next()) {
				producto.setIdProducto(rs.getString("idProducto"));
				producto.setDescripcion(rs.getString("Descripcion"));
				producto.setDescripPrint(rs.getString("DescriPrint"));
				producto.setGrupo(rs.getInt("Grupo"));
				producto.setTipo(rs.getInt("Tipo"));
				producto.setUniCompra(rs.getString("UniCompra"));
				producto.setValCompra(rs.getBigDecimal("ValCompra"));
				producto.setUniVenta(rs.getString("UniVenta"));
				producto.setValVenta(rs.getBigDecimal("valVenta"));
				producto.setUniInvent(rs.getString("UniInvent"));
				producto.setValInvent(rs.getBigDecimal("ValInvent"));
				producto.setSerie(rs.getString("Serie"));
				producto.setLote(rs.getString("Lote"));
			}
		} catch (Exception e) {
			producto = null;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return producto;
	}

	@Override
	public String getCorrelativo() throws SQLException {
		String correlativo = "";
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		Statement st = null;
		ResultSet rs;
		String query = "SELECT count(idProducto) + 1 as correlativo "
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

	@Override
	public String save(Object entity) throws SQLException {
		Productos producto = (Productos) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "INSERT INTO Producto(Descripcion, DescriPrint, Grupo, Tipo, UniCompra, \n"
				+ "ValCompra, UniVenta, ValInvent, UniInvent, ValInvent, Serie, Lote)\n"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getDescripcion());
			ps.setString(2, producto.getDescripPrint());
			ps.setInt(3, producto.getGrupo());
			ps.setInt(4, producto.getTipo());
			ps.setString(5, producto.getUniCompra());
			ps.setBigDecimal(6, producto.getValCompra());
			ps.setString(7, producto.getUniVenta());
			ps.setBigDecimal(8, producto.getValVenta());
			ps.setString(9, producto.getUniInvent());
			ps.setBigDecimal(10, producto.getValInvent());
			ps.setString(11, producto.getSerie());
			ps.setString(12, producto.getLote());

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
	
//	@Override
//	public String save(Object entity) throws SQLException {
//		ProductoDaoRequest producto = (ProductoDaoRequest) entity;
//		SqlConn sconn = new SqlConn();
//		Connection conn = sconn.getConnection();
//		String codigoRetorno = "0";
//		String query = "INSERT INTO dbo.Producto (IdProducto, Descripcion, DescriPrint, Grupo, Tipo,\r\n"
//				+ "UniCompra, ValCompra, UniVenta, UniInvent, ValInvent, Serie, Lote)"
//				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
//		try {
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, producto.getIdProducto());
//			ps.setString(2, producto.getDescripcion());
//			ps.setString(3, producto.getDescriPrint());
//			ps.setInt(4, producto.getGrupo());
//			ps.setInt(5, producto.getTipo());
//			ps.setString(6, producto.getUniCompra());
//			ps.setInt(7, producto.getValCompra());
//			ps.setString(8, producto.getUniVenta());
//			ps.setInt(9, producto.getValVenta());
//			ps.setString(10, producto.getUniInvent());
//			ps.setInt(11, producto.getValInvent());
//			ps.setString(12, producto.getSerie());
//			ps.setString(13, producto.getLote());
//
//
//			ps.executeUpdate();
//			conn.commit();
//		} catch (SQLException e) {
//			conn.rollback();
//			e.printStackTrace();
//			codigoRetorno = String.valueOf(e.getErrorCode());
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return codigoRetorno;
//	}

	@Override
	public String edit(Object entity) throws SQLException {
		Productos producto = (Productos) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "UPDATE dbo.Producto\r\n"
				+ "SET Descripcion = ?, \r\n"
				+ "DescriPrint= ?, \r\n"
				+ "Grupo= ?,\r\n"
				+ "Tipo= ?, \r\n"
				+ "UniCompra= ?, \r\n"
				+ "ValCompra= ?, \r\n"
				+ "UniVenta= ?, \r\n"
				+ "UniInvent= ?, \r\n"
				+ "ValInvent= ?, \r\n"
				+ "Serie= ?, \r\n"
				+ "Lote= ? \r\n"
				+ "WHERE IdProducto = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getDescripcion());
			ps.setString(2, producto.getDescripPrint());
			ps.setInt(3, producto.getGrupo());
			ps.setInt(4, producto.getTipo());
			ps.setString(5, producto.getUniCompra());
			ps.setBigDecimal(6, producto.getValCompra());
			ps.setString(7, producto.getUniVenta());
			ps.setBigDecimal(8, producto.getValVenta());
			ps.setString(9, producto.getUniInvent());
			ps.setBigDecimal(10, producto.getValInvent());
			ps.setString(11, producto.getSerie());
			ps.setString(12, producto.getLote());
			ps.setString(13, producto.getIdProducto());
			

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
		Productos producto = (Productos) entity;
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String codigoRetorno = "0";
		String query = "DELETE FROM dbo.Producto\r\n"
				+ "WHERE idProducto = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, producto.getIdProducto());


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

}
