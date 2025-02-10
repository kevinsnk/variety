package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Movimientos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CuentasxCobrarDaoResponse {

	public String codigo;
	public String descripcion;
	public List<Movimientos> movimientos;
}
