package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.BodegaDaoRequest;
import com.erp.variety.dao.BodegaDaoResponse;
import com.erp.variety.jdbc.BodegaJDBC;
import com.erp.variety.model.Bodega;

public class BodegaController {

	@GetMapping("/getAll")
	public BodegaDaoResponse getAllBodega() {
		BodegaJDBC bodegaJDBC = new BodegaJDBC();
		BodegaDaoResponse bodegaDaoResponse = new BodegaDaoResponse();
		List<Bodega> listaBodega = new ArrayList<>();
		try {
			listaBodega = bodegaJDBC.findAll();
			bodegaDaoResponse.setIdBodega("0");
			bodegaDaoResponse.setDescripcion("success");
			bodegaDaoResponse.setBodega(listaBodega);
		} catch (SQLException e) {
			bodegaDaoResponse.setIdBodega(String.valueOf(e.getErrorCode()));
			bodegaDaoResponse.setDescripcion(e.getMessage());
		}
		
		return bodegaDaoResponse;
	}
	
	@GetMapping("/getBodega")
	public BodegaDaoResponse getBodega() throws SQLException {
		BodegaJDBC bodegaJDBC = new BodegaJDBC();
		Bodega bodega = new Bodega();
		List<Bodega> listaBodega = new ArrayList<>();
		BodegaDaoResponse bodegaDaoResponse = new BodegaDaoResponse();
		try {
			bodega = bodegaJDBC.getRecord(bodega);
			if(bodega != null) {
				listaBodega.add(bodega);
			}
			bodegaDaoResponse.setIdBodega("0");
			bodegaDaoResponse.setDescripcion("success");
			bodegaDaoResponse.setBodega(listaBodega);
		} catch (SQLException e) {
			bodegaDaoResponse.setIdBodega(String.valueOf(e.getErrorCode()));
			bodegaDaoResponse.setDescripcion(e.getMessage());
		}
		return bodegaDaoResponse;
	}
	
	@PostMapping("/saveBodega")
	public BodegaDaoResponse saveBodega(@RequestBody BodegaDaoRequest bodega) {
		BodegaJDBC bodegaJDBC = new BodegaJDBC();
		BodegaDaoResponse bodegaDaoResponse = new BodegaDaoResponse();
		String codigoRespuesta = "0";
		String idBodega = "0";
		try {
			System.out.println("cliente " + bodega);
			idBodega = bodegaJDBC.getCorrelativo();
			bodega.setIdBodega(idBodega);
			codigoRespuesta = bodegaJDBC.save(bodega);
			if(codigoRespuesta.equals("0")) {
				bodegaDaoResponse.setDescripcion("Registro guardado exitosamente");
			}else {
				bodegaDaoResponse.setDescripcion("Error al querer guardar el nuevo cliente en la tabla");
			}
			bodegaDaoResponse.setIdBodega(codigoRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			bodegaDaoResponse.setIdBodega(codigoRespuesta);
			bodegaDaoResponse.setDescripcion(e.getMessage());
		}
		
		return bodegaDaoResponse;
	}
	
	@PostMapping("/editBodega")
	public BodegaDaoResponse editBodega(@RequestBody BodegaDaoRequest bodega) {
		BodegaJDBC bodegaJDBC = new BodegaJDBC();
		BodegaDaoResponse bodegaDaoResponse = new BodegaDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = bodegaJDBC.edit(bodega);
			if(codigoRespuesta.equals("0")) {
				bodegaDaoResponse.setDescripcion("Registro editado exitosamente");
			}else {
				bodegaDaoResponse.setDescripcion("Error al querer guardar los cambios del cliente en la tabla");
			}
			bodegaDaoResponse.setIdBodega(codigoRespuesta);
		} catch (Exception e) {
			bodegaDaoResponse.setIdBodega(codigoRespuesta);
			bodegaDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return bodegaDaoResponse;
	}
	
	@PostMapping("/deleteBodega")
	public BodegaDaoResponse deleteBodega(@RequestBody BodegaDaoRequest bodega) {
		BodegaJDBC bodegaJDBC = new BodegaJDBC();
		BodegaDaoResponse bodegaDaoResponse = new BodegaDaoResponse();
		String codigoRespuesta = "0";
		try {
			if(bodega != null && !bodega.getIdBodega().trim().equals("")) {
				codigoRespuesta = bodegaJDBC.delete(bodega);
				if(codigoRespuesta.equals("0")) {
					bodegaDaoResponse.setDescripcion("Registro eliminado exitosamente");
				}else {
					bodegaDaoResponse.setDescripcion("Error al querer eliminar cliente en la tabla");
				}
				bodegaDaoResponse.setIdBodega(codigoRespuesta);
			}else {
				bodegaDaoResponse.setIdBodega("1");
				bodegaDaoResponse.setDescripcion("Debe de mandar un código de cliente valido.");
			}
		} catch (SQLException e) {
			bodegaDaoResponse.setIdBodega(codigoRespuesta);
			bodegaDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return bodegaDaoResponse;
	}
}
