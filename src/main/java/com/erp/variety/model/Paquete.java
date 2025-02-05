package com.erp.variety.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Paquete {
	private String idPaquete;
	private String descripcion;
	private BigDecimal pCosto;
	private BigDecimal pVenta;
	private BigDecimal saldo;
	private Date fechaAsignacion;
	private Bodega idBodega;
	private String entregado;
	private Date pagoaFecha;
	private Clientes cliente;
	private List<DetaPaquete> detallePaquete;

}
