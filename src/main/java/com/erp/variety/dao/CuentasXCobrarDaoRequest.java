package com.erp.variety.dao;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CuentasXCobrarDaoRequest {

	private int idCobro;
	private Date fechaCobro;
	private int estado;
	private String idCliente;
	private BigDecimal montoPagado;

}
