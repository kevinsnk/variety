package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.PaqueteDaoResponse;
import com.erp.variety.dao.ZonaDaoResponse;
import com.erp.variety.jdbc.PaqueteJDBC;
import com.erp.variety.jdbc.ZonaJDBC;
import com.erp.variety.model.Paquete;
import com.erp.variety.model.Zona;


@RestController
@RequestMapping("/Paquete")
public class PaqueteController {


	@GetMapping("/getAll")
	public PaqueteDaoResponse getAllZona() {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		List<Paquete> listapaquetes = new ArrayList<>();
		try {
			listapaquetes = paqueteJDBC.findAll();
			paqueteDaoResponse.setIdPaquete("0");
			paqueteDaoResponse.setDescripcion("EXITO");
			paqueteDaoResponse.setPCosto(0);
			paqueteDaoResponse.setPVenta(0);
			
			paqueteDaoResponse.setPaquete(listapaquetes);
			
		} catch (SQLException e) {
			paqueteDaoResponse.setIdPaquete(String.valueOf(e.getErrorCode()));
			paqueteDaoResponse.setDescripcion(e.getMessage());
		}
		
		return paqueteDaoResponse;
	}
	
	@GetMapping("/getPaquete")
	public Paquete getPaquete() throws SQLException {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		Paquete paquete = new Paquete();
		List<Paquete> listapaquetes = new ArrayList<>();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		try {
			paquete = paqueteJDBC.getRecord(paquete);
			if(paquete != null) {
				listapaquetes.add(paquete);
			}
			paqueteDaoResponse.setIdPaquete("0");
			paqueteDaoResponse.setDescripcion("EXITO");
			paqueteDaoResponse.setPCosto(0);
			paqueteDaoResponse.setPVenta(0);
			paqueteDaoResponse.setPaquete(listapaquetes);
			
		} catch (SQLException e) {
			paqueteDaoResponse.setIdPaquete(String.valueOf(e.getErrorCode()));
			paqueteDaoResponse.setDescripcion(e.getMessage());
		}
		return paquete;
	}
	
	@PostMapping("/savePaquete")
	public void savePaquete(Paquete paquete) {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		try {
			paqueteJDBC.save(paquete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/editPaquete")
	public void editPaquete(Paquete paquete) {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		try {
			paqueteJDBC.edit(paquete);
		} catch (SQLException e) {
			paqueteDaoResponse.setIdPaquete(String.valueOf(e.getErrorCode()));
			paqueteDaoResponse.setDescripcion(e.getMessage());
			
		}
		
	}
	
	@PostMapping("/deletePaquete")
	public void deletePaquete(Paquete paquete) {
		
	}
}
