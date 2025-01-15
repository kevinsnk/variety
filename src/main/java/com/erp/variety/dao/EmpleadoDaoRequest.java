package com.erp.variety.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmpleadoDaoRequest {

	public int idEmpleado;
	public String nombreEmpleado;
	public String apellidoEmpleado;
	public String direccionEmpleado;
	public String telefonoEmpleado;
	public String celularEmpleado;
	public String emailEmpleado;
	public int activo;
	public String tipoEmpleado;
	
}
