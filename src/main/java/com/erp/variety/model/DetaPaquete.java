package com.erp.variety.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DetaPaquete {

	private int idDetaPaquete;
	private String idPaquete;
	private String idProducto;
	private String detaProducto;
	private BigDecimal costo;
	private BigDecimal venta;
	private int cantidad;
	private int idBodega;
}
