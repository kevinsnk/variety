package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.ProductosDaoResponse;
import com.erp.variety.jdbc.ProductosJDBC;
import com.erp.variety.model.Productos;

@RestController
@RequestMapping("/productos")
public class ProductosController {

	@GetMapping("/getAll")
	public ProductosDaoResponse getAllProductos() {
		ProductosJDBC productosJDBC = new ProductosJDBC();
		ProductosDaoResponse productosDaoResponse = new ProductosDaoResponse();
		List<Productos> listaProductos = new ArrayList<>();
		try {
			listaProductos = productosJDBC.findAll();
			productosDaoResponse.setCodigo("0");
			productosDaoResponse.setDescripcion("success");
			productosDaoResponse.setProductos(listaProductos);

		} catch (SQLException e) {
			productosDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			productosDaoResponse.setDescripcion(e.getMessage());
		}

		return productosDaoResponse;
	}

	@GetMapping("/getproducto")
	public ProductosDaoResponse getproducto(@RequestBody Integer idProducto) throws SQLException {
		ProductosJDBC productosJDBC = new ProductosJDBC();
		Productos productos = new Productos();
		List<Productos> listaProductos = new ArrayList<>();
		ProductosDaoResponse productosDaoResponse = new ProductosDaoResponse();
		try {
			productos = productosJDBC.getRecord(idProducto);
			if (productos != null) {
				listaProductos.add(productos);
			}
			productosDaoResponse.setCodigo("0");
			productosDaoResponse.setDescripcion("success");
			productosDaoResponse.setProductos(listaProductos);

		} catch (SQLException e) {
			productosDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			productosDaoResponse.setDescripcion(e.getMessage());
		}
		return productosDaoResponse;
	}

	@PostMapping("/saveProducto")
	public ProductosDaoResponse saveProducto(@RequestBody Productos producto) {
		ProductosJDBC productosJDBC = new ProductosJDBC();
		ProductosDaoResponse productosDaoResponse = new ProductosDaoResponse();
		String idProducto = "0";
		String codigoRespuesta = "0";
		try {
			idProducto = productosJDBC.getCorrelativo();
			producto.setIdProducto(idProducto);
			codigoRespuesta = productosJDBC.save(producto);
			if (codigoRespuesta.equals("0")) {
				productosDaoResponse.setDescripcion("Registro guardado exitosamente");
			} else {
				productosDaoResponse.setDescripcion("Error al querer guardar el nuevo producto en la tabla");
			}
			productosDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			productosDaoResponse.setCodigo(codigoRespuesta);
			productosDaoResponse.setDescripcion(e.getMessage());
		}
		return productosDaoResponse;
	}

	@PostMapping("/editProducto")
	public ProductosDaoResponse editProducto(@RequestBody Productos producto) {
		ProductosJDBC productosJDBC = new ProductosJDBC();
		ProductosDaoResponse productosDaoResponse = new ProductosDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = productosJDBC.edit(producto);
			if (codigoRespuesta.equals("0")) {
				productosDaoResponse.setDescripcion("Registro editado exitosamente");
			} else {
				productosDaoResponse.setDescripcion("Error al querer guardar los cambios del producto en la tabla");
			}
			productosDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			productosDaoResponse.setCodigo(codigoRespuesta);
			productosDaoResponse.setDescripcion(e.getMessage());

		}
		return productosDaoResponse;
	}

	@PostMapping("/deleteProducto")
	public ProductosDaoResponse deleteProducto(@RequestBody Productos producto) {
		ProductosJDBC productosJDBC = new ProductosJDBC();
		ProductosDaoResponse productosDaoResponse = new ProductosDaoResponse();
		String codigoRespuesta = "0";
		try {
			if (producto != null && !producto.getIdProducto().equals("0")) {
				codigoRespuesta = productosJDBC.delete(producto);
				if (codigoRespuesta.equals("0")) {
					productosDaoResponse.setDescripcion("Registro eliminado exitosamente");
				} else {
					productosDaoResponse.setDescripcion("Error al querer eliminar el producto en la tabla");
				}
				productosDaoResponse.setCodigo(codigoRespuesta);
			} else {
				productosDaoResponse.setCodigo("1");
				productosDaoResponse.setDescripcion("Debe de mandar un código de pedido valido.");
			}
		} catch (Exception e) {
			productosDaoResponse.setCodigo(codigoRespuesta);
			productosDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return productosDaoResponse;
	}
}
