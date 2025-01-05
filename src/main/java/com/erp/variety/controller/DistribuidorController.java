package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.DistribuidorDaoResponse;
import com.erp.variety.jdbc.DistribuidorJDBC;
import com.erp.variety.model.Distribuidor;

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
			distribuidorDaoResponse.setCodigo("0");
			distribuidorDaoResponse.setDescripcion("EXITO");
			distribuidorDaoResponse.setDistribuidores(listadistribuidor);
		} catch (SQLException e) {
			distribuidorDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			distribuidorDaoResponse.setDescripcion(e.getMessage());
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
			distribuidorDaoResponse.setCodigo("0");
			distribuidorDaoResponse.setDescripcion("EXITO");
			distribuidorDaoResponse.setDistribuidores(listadistribuidor);
		} catch (SQLException e) {
			distribuidorDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			distribuidorDaoResponse.setDescripcion(e.getMessage());
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
			distribuidorDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			distribuidorDaoResponse.setDescripcion(e.getMessage());
			
		}
		
	}
	
	@PostMapping("/deleteDistribuidor")
	public void deleteDistribuidor(Distribuidor distribuidor) {
		
	}
}
