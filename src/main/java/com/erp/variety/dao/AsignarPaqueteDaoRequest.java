package com.erp.variety.dao;


import java.util.List;

import com.erp.variety.model.Paquete;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AsignarPaqueteDaoRequest {

	public String idBodega;
	public List<Paquete> listaPaquetes;
}
