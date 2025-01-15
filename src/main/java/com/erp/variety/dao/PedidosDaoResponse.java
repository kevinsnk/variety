package com.erp.variety.dao;

import java.util.List;

import com.erp.variety.model.Pedidos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PedidosDaoResponse {

	public String codigo;
	public String descripcion;
	public List<Pedidos> pedidos;
	
}
