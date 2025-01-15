package com.erp.variety.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Pedidos {

	public int idPedido;
	public String cancelado;
	public String estado;
	public String tipo;
	public String numero;
	public Date fecha;
	public Date fechaEntrega;
	public Clientes cliente;
	public Empleado empleado;
	public BigDecimal sumas;
	public BigDecimal impuesto;
	public BigDecimal total;
	
}
