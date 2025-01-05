package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Distribuidor;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class DistribuidorDaoResponse {
	public String codigo;
	public String descripcion;
	public List<Distribuidor> Distribuidores;
}
