package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.ZonaDaoResponse;
import com.erp.variety.jdbc.ZonaJDBC;
import com.erp.variety.model.Zona;


@RestController
@RequestMapping("/Zonas")
public class ZonaController {

	@GetMapping("/getAll")
	public ZonaDaoResponse getAllZona() {
		ZonaJDBC zonaJDBC = new ZonaJDBC();
		ZonaDaoResponse zonaDaoResponse = new ZonaDaoResponse();
		List<Zona> listaZonas = new ArrayList<>();
		try {
			listaZonas = zonaJDBC.findAll();
			zonaDaoResponse.setIdZona("0");
			zonaDaoResponse.setDescripcion("EXITO");
			zonaDaoResponse.setZonas(listaZonas);
		} catch (SQLException e) {
			zonaDaoResponse.setIdZona(String.valueOf(e.getErrorCode()));
			zonaDaoResponse.setDescripcion(e.getMessage());
		}
		
		return zonaDaoResponse;
	}
	
	@GetMapping("/getZona")
	public Zona getZona() throws SQLException {
		ZonaJDBC zonaJDBC = new ZonaJDBC();
		Zona zona = new Zona();
		List<Zona> listaZona = new ArrayList<>();
		ZonaDaoResponse zonaDaoResponse = new ZonaDaoResponse();
		try {
			zona = zonaJDBC.getRecord(zona);
			if(zona != null) {
				listaZona.add(zona);
			}
			zonaDaoResponse.setIdZona("0");
			zonaDaoResponse.setDescripcion("EXITO");
			zonaDaoResponse.setZonas(listaZona);
		} catch (SQLException e) {
			zonaDaoResponse.setIdZona(String.valueOf(e.getErrorCode()));
			zonaDaoResponse.setDescripcion(e.getMessage());
		}
		return zona;
	}
	
	@PostMapping("/saveZona")
	public void saveZona(Zona zona) {
		ZonaJDBC zonaJDBC = new ZonaJDBC();
		try {
			zonaJDBC.save(zona);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/editZona")
	public void editZona(Zona zona) {
		ZonaJDBC zonaJDBC = new ZonaJDBC();
		ZonaDaoResponse zonaDaoResponse = new ZonaDaoResponse();
		try {
			zonaJDBC.edit(zona);
		} catch (SQLException e) {
			zonaDaoResponse.setIdZona(String.valueOf(e.getErrorCode()));
			zonaDaoResponse.setDescripcion(e.getMessage());
			
		}
		
	}
	
	@PostMapping("/deleteZona")
	public void deleteZona(Zona zona) {
		
	}
	
}
