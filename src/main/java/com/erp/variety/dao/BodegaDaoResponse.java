package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Bodega;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class BodegaDaoResponse {
	
	public String IdBodega;
	public String Descripcion;
	public String UbicacionFi;
	public List<Bodega> bodega;

}
