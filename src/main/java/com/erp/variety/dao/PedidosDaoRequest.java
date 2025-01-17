package com.erp.variety.dao;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PedidosDaoRequest {

	public int idPedido;
	public String cancelado;
	public String estado;
	public String tipo;
	public String numero;
	public Date fecha;
	public Date fechaEntrega;
	public String idCliente;
	public String nombreCliente;
	public int idEmpleado;
	public BigDecimal sumas;
	public BigDecimal impuesto;
	public BigDecimal total;

}
