package com.erp.variety.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Movimientos {

	private String descripcion;
	private BigDecimal debito;
	private BigDecimal credito;
	private BigDecimal saldo;
	private Clientes cliente;
	private Date fechaMovimiento;
}
