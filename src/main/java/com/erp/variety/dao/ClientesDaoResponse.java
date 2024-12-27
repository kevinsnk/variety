package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Clientes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ClientesDaoResponse {

	public String codigo;
	public String descripcion;
	public List<Clientes> clientes;
}
