package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Productos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductosDaoResponse {

	public String codigo;
	public String descripcion;
	public List<Productos> productos;
}
