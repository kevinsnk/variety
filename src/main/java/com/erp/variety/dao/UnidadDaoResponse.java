package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Unidad;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UnidadDaoResponse {

	public String codigo;
	public String descripcion;
	public List<Unidad> unidades;
}
