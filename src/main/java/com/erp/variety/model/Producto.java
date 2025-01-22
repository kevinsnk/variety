package com.erp.variety.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Producto {
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
	
} 
