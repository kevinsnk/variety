package com.erp.variety.dao;

import java.util.List;


import com.erp.variety.model.Paquete;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class PaqueteDaoResponse {

	public String codigo;
	public String descripcion;
	public List<Paquete> paquete;
	
	
	
}
