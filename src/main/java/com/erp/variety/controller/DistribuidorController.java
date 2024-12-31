package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.DistribuidorDaoResponse;
import com.erp.variety.dao.ZonaDaoResponse;
import com.erp.variety.jdbc.DistribuidorJDBC;
import com.erp.variety.jdbc.ZonaJDBC;
import com.erp.variety.model.Distribuidor;
import com.erp.variety.model.Zona;

@RestController
@RequestMapping("/Distribuidor")
public class DistribuidorController {

	@GetMapping("/getAll")
	public DistribuidorDaoResponse getAllDistribuidor() {
		DistribuidorJDBC distribuidorJDBC = new DistribuidorJDBC();
		DistribuidorDaoResponse distribuidorDaoResponse = new DistribuidorDaoResponse();
		List<Distribuidor> listadistribuidor = new ArrayList<>();
		try {
			listadistribuidor = distribuidorJDBC.findAll();
			distribuidorDaoResponse.setIdDistribuidor("0");
			distribuidorDaoResponse.setNombre("EXITO");
			distribuidorDaoResponse.setDistribuidores(listadistribuidor);
		} catch (SQLException e) {
			distribuidorDaoResponse.setIdDistribuidor(String.valueOf(e.getErrorCode()));
			distribuidorDaoResponse.setNombre(e.getMessage());
		}
		
		return distribuidorDaoResponse;
	}
	
	@GetMapping("/getDistribuidor")
	public Distribuidor getDistribuidor() throws SQLException {
		DistribuidorJDBC distribuidorJDBC = new DistribuidorJDBC();
		Distribuidor distribuidor = new Distribuidor();
		List<Distribuidor> listadistribuidor = new ArrayList<>();
		DistribuidorDaoResponse distribuidorDaoResponse = new DistribuidorDaoResponse();
		try {
			distribuidor = distribuidorJDBC.getRecord(distribuidor);
			if(distribuidor != null) {
				listadistribuidor.add(distribuidor);
			}
			distribuidorDaoResponse.setIdDistribuidor("0");
			distribuidorDaoResponse.setNombre("EXITO");
			distribuidorDaoResponse.setDistribuidores(listadistribuidor);
		} catch (SQLException e) {
			distribuidorDaoResponse.setIdDistribuidor(String.valueOf(e.getErrorCode()));
			distribuidorDaoResponse.setNombre(e.getMessage());
		}
		return distribuidor;
	}
	
	@PostMapping("/saveDistribuidor")
	public void saveDistribuidor(Distribuidor distribuidor) {
		DistribuidorJDBC distribuidorJDBC = new DistribuidorJDBC();
		try {
			distribuidorJDBC.save(distribuidor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/editDistribuidor")
	public void editDistribuidor(Distribuidor distribuidor) {
		DistribuidorJDBC distribuidorJDBC = new DistribuidorJDBC();
		DistribuidorDaoResponse distribuidorDaoResponse = new DistribuidorDaoResponse();
		try {
			distribuidorJDBC.edit(distribuidor);
		} catch (SQLException e) {
			distribuidorDaoResponse.setIdDistribuidor(String.valueOf(e.getErrorCode()));
			distribuidorDaoResponse.setNombre(e.getMessage());
			
		}
		
	}
	
	@PostMapping("/deleteDistribuidor")
	public void deleteDistribuidor(Distribuidor distribuidor) {
		
	}
}
