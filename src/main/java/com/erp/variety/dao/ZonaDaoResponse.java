package com.erp.variety.dao;

import java.util.List;


import com.erp.variety.model.Zona;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class ZonaDaoResponse {

	public String IdZona;
	public String Descripcion;
	public int idEmpleado;
	public List<Zona> zonas;
}
