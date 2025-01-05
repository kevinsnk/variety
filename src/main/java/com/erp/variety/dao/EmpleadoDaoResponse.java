package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Clientes;
import com.erp.variety.model.Empleado;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmpleadoDaoResponse {
	public String codigo;
	public String descripcion;
	public List<Empleado> empleados;
}
