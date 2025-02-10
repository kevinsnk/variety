package com.erp.variety.model;

import java.math.BigDecimal;

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
	private String idCliente;
}
