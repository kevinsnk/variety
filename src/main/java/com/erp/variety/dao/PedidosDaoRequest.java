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

	private String idPaquete;
	//private String descripcion;
	//private BigDecimal pCosto;
	//private BigDecimal pVenta;
	private BigDecimal saldo;
	private Date fechaAsignacion;
	//private Bodega idBodega;
	private int entregado;
	//private Date pagoaFecha;
	private String idCliente;

}
