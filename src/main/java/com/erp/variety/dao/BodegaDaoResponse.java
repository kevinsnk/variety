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
	
	public String codigo;
	public String descripcion;
	public List<Bodega> bodega;

}
