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
	public String IdDistribuidor;
	public String Nombre;
	public String Apellido;
	public String Direccion;
	public String Departamento;
	public String Municipio;
	public String Sexo;
	public String Dui;
	public String Telefono;
	public String Celular;
	public String whatsapp;
	public String Zona;
	public String Email;
	public List<Distribuidor> Distribuidores;
}
