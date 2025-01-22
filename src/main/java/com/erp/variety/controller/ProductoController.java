package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.erp.variety.dao.ProductoDaoRequest;
import com.erp.variety.dao.ProductoDaoResponse;
import com.erp.variety.jdbc.ProductoJDBC;
import com.erp.variety.model.Producto;

@RestController
@RequestMapping("/producto")

public class ProductoController {

	@GetMapping("/getAll")
	public ProductoDaoResponse getAllProducto() {
		ProductoJDBC productoJDBC = new ProductoJDBC();
		ProductoDaoResponse productoDaoResponse = new ProductoDaoResponse();
		List<Producto> listaProducto = new ArrayList<>();
		try {
			listaProducto= productoJDBC.findAll();
			productoDaoResponse.setIdProducto("0");
			productoDaoResponse.setDescripcion("success");
			productoDaoResponse.setProducto(listaProducto);
		} catch (SQLException e) {
			productoDaoResponse.setIdProducto(String.valueOf(e.getErrorCode()));
			productoDaoResponse.setDescripcion(e.getMessage());
		}
		
		return productoDaoResponse;
	}
	
	@GetMapping("/getProducto")
	public ProductoDaoResponse getClient() throws SQLException {
		ProductoJDBC productoJDBC = new ProductoJDBC();
		Producto producto = new Producto();
		List<Producto> listaProducto = new ArrayList<>();
		ProductoDaoResponse productoDaoResponse = new ProductoDaoResponse();
		try {
			producto = productoJDBC.getRecord(producto);
			if(producto != null) {
				listaProducto.add(producto);
			}
			productoDaoResponse.setIdProducto("0");
			productoDaoResponse.setDescripcion("success");
			productoDaoResponse.setProducto(listaProducto);
		} catch (SQLException e) {
			productoDaoResponse.setIdProducto(String.valueOf(e.getErrorCode()));
			productoDaoResponse.setDescripcion(e.getMessage());
		}
		return productoDaoResponse;
	}
	
	@PostMapping("/saveProducto")
	public ProductoDaoResponse saveClient(@RequestBody ProductoDaoRequest producto) {
		ProductoJDBC productoJDBC = new ProductoJDBC();
		ProductoDaoResponse productoDaoResponse = new ProductoDaoResponse();
		String codigoRespuesta = "0";
		String idProducto = "0";
		try {
			System.out.println("producto " + producto);
			idProducto = productoJDBC.getCorrelativo();
			producto.setIdProducto(idProducto);
			codigoRespuesta = productoJDBC.save(producto);
			if(codigoRespuesta.equals("0")) {
				productoDaoResponse.setDescripcion("Registro guardado exitosamente");
			}else {
				productoDaoResponse.setDescripcion("Error al querer guardar el nuevo cliente en la tabla");
			}
			productoDaoResponse.setIdProducto(codigoRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			productoDaoResponse.setIdProducto(codigoRespuesta);
			productoDaoResponse.setDescripcion(e.getMessage());
		}
		
		return productoDaoResponse;
	}
	
	@PostMapping("/editProducto")
	public ProductoDaoResponse editProducto(@RequestBody ProductoDaoRequest producto) {
		ProductoJDBC productoJDBC = new ProductoJDBC();
		ProductoDaoResponse productoDaoResponse = new ProductoDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = productoJDBC.edit(producto);
			if(codigoRespuesta.equals("0")) {
				productoDaoResponse.setDescripcion("Registro editado exitosamente");
			}else {
				productoDaoResponse.setDescripcion("Error al querer guardar los cambios del cliente en la tabla");
			}
			productoDaoResponse.setIdProducto(codigoRespuesta);
		} catch (Exception e) {
			productoDaoResponse.setIdProducto(codigoRespuesta);
			productoDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return productoDaoResponse;
	}
	
	@PostMapping("/deleteProducto")
	public ProductoDaoResponse deleteProducto(@RequestBody ProductoDaoRequest producto) {
		ProductoJDBC productoJDBC = new ProductoJDBC();
		ProductoDaoResponse productoDaoResponse = new ProductoDaoResponse();
		String codigoRespuesta = "0";
		try {
			if(producto != null && !producto.getIdProducto().trim().equals("")) {
				codigoRespuesta = productoJDBC.delete(producto);
				if(codigoRespuesta.equals("0")) {
					productoDaoResponse.setDescripcion("Registro eliminado exitosamente");
				}else {
					productoDaoResponse.setDescripcion("Error al querer eliminar cliente en la tabla");
				}
				productoDaoResponse.setIdProducto(codigoRespuesta);
			}else {
				productoDaoResponse.setIdProducto("1");
				productoDaoResponse.setDescripcion("Debe de mandar un código de cliente valido.");
			}
		} catch (SQLException e) {
			productoDaoResponse.setIdProducto(codigoRespuesta);
			productoDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return productoDaoResponse;
	}
}
