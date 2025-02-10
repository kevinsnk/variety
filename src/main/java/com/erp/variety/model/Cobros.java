package com.erp.variety.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Cobros {

	private int idCobro;
	private Date fechaCobro;
	private int estado;
	private Clientes cliente;
	private BigDecimal montoPagado;
	
}
