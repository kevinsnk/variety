package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.PaqueteDaoResponse;
import com.erp.variety.jdbc.PaqueteJDBC;
import com.erp.variety.model.Paquete;

@RestController
@RequestMapping("/paquete")
public class PaqueteController {

	@GetMapping("/getAll")
	public PaqueteDaoResponse getAllZona() {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		List<Paquete> listapaquetes = new ArrayList<>();
		try {
			listapaquetes = paqueteJDBC.findAll();
			paqueteDaoResponse.setCodigo("0");
			paqueteDaoResponse.setDescripcion("success");
			paqueteDaoResponse.setPaquete(listapaquetes);

		} catch (SQLException e) {
			paqueteDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
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
			if (paquete != null) {
				listapaquetes.add(paquete);
			}
			paqueteDaoResponse.setCodigo("0");
			paqueteDaoResponse.setDescripcion("success");
			paqueteDaoResponse.setPaquete(listapaquetes);

		} catch (SQLException e) {
			paqueteDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			paqueteDaoResponse.setDescripcion(e.getMessage());
		}
		return paquete;
	}

	@PostMapping("/savePaquete")
	public PaqueteDaoResponse savePaquete(@RequestBody Paquete paquete) {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		String idPaquete = "0";
		String codigoRespuesta = "0";
		try {
			idPaquete = paqueteJDBC.getCorrelativo();
			paquete.setIdPaquete(idPaquete);
			codigoRespuesta = paqueteJDBC.save(paquete);
			if (codigoRespuesta.equals("0")) {
				paqueteDaoResponse.setDescripcion("Registro guardado exitosamente");
			} else {
				paqueteDaoResponse.setDescripcion("Error al querer guardar el nuevo paquete en la tabla");
			}
			paqueteDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			paqueteDaoResponse.setCodigo(codigoRespuesta);
			paqueteDaoResponse.setDescripcion(e.getMessage());
		}
		return paqueteDaoResponse;
	}

	@PostMapping("/editPaquete")
	public PaqueteDaoResponse editPaquete(@RequestBody Paquete paquete) {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = paqueteJDBC.edit(paquete);
			if (codigoRespuesta.equals("0")) {
				paqueteDaoResponse.setDescripcion("Registro editado exitosamente");
			} else {
				paqueteDaoResponse.setDescripcion("Error al querer guardar los cambios del paquete en la tabla");
			}
			paqueteDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			paqueteDaoResponse.setCodigo(codigoRespuesta);
			paqueteDaoResponse.setDescripcion(e.getMessage());

		}
		return paqueteDaoResponse;
	}

	@PostMapping("/deletePaquete")
	public PaqueteDaoResponse deletePaquete(@RequestBody String paquete) {
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		PaqueteDaoResponse paqueteDaoResponse = new PaqueteDaoResponse();
		String codigoRespuesta = "0";
		try {
			if (paquete != null && !paquete.trim().equals("")) {
				codigoRespuesta = paqueteJDBC.delete(paquete);
				if (codigoRespuesta.equals("0")) {
					paqueteDaoResponse.setDescripcion("Registro eliminado exitosamente");
				} else {
					paqueteDaoResponse.setDescripcion("Error al querer eliminar el paquete en la tabla");
				}
				paqueteDaoResponse.setCodigo(codigoRespuesta);
			} else {
				paqueteDaoResponse.setCodigo("1");
				paqueteDaoResponse.setDescripcion("Debe de mandar un código de paquete valido.");
			}
		} catch (Exception e) {
			paqueteDaoResponse.setCodigo(codigoRespuesta);
			paqueteDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return paqueteDaoResponse;
	}
}
