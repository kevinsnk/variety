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

	public String codigo;
	public String descripcion;
	public List<Zona> zonas;
}
