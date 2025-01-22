package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Clientes;
import com.erp.variety.model.Producto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class ProductoDaoResponse {
	public String idProducto;
	public String descripcion;
	public String descriPrint;
	public int grupo;
	public int tipo;
	public String uniCompra;
	public int valCompra;
	public String uniVenta;
	public int valVenta;
	public String uniInvent;
	public int valInvent;
	public String serie;
	public String lote;
	public List<Producto> producto;
}
