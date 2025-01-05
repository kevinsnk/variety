package com.erp.variety.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class Empleado {

	public int idEmpleado;
	public String nombreEmpleado;
	public String apellidoempleado;
	public String direccionEmpleado;
	public String telefonoEmpleado;
	public String celularEmpleado;
	public String emailEmpleado;
	public int activo;
	public String tipoEmpleado;
	
}
