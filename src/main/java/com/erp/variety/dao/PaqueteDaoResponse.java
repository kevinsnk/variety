package com.erp.variety.dao;

import java.util.List;


import com.erp.variety.model.Paquete;
import com.erp.variety.model.Zona;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class PaqueteDaoResponse {

	public String IdPaquete;
	public String Descripcion;
	public double pCosto;
	public double pVenta;
	public List<Paquete> paquete;
	
	
	
}
