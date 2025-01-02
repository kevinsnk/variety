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
	public int IdEmpleado;
	public String NombreEmpleado;
	public String Apellidoempleado;
	public String DireccionEmpleado;
	public String TelefonoEmpleado;
	public String CelularEmpleado;
	public String emailEmpleado;
	public int Activo;
	public String TipoEmpleado;
	public List<Empleado> empleados;
}
