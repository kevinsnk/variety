package com.erp.variety.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Productos {
	
	public String idProducto;
	public String descripcion;
	public String descripPrint;
	public int grupo;
	public int tipo;
	public String uniCompra;
	public BigDecimal valCompra;
	public String uniVenta;
	public BigDecimal valVenta;
	public String uniInvent;
	public BigDecimal valInvent;
	public String serie;
	public String lote;
}
