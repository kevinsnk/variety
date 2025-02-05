package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.DetaPaquete;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DetaPaqueteDaoResponse {

	public String codigo;
	public String descripcion;
	public List<DetaPaquete> detaPaquetes;
}
