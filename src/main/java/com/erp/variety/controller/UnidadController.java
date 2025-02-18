package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.UnidadDaoResponse;
import com.erp.variety.jdbc.UnidadJDBC;
import com.erp.variety.model.Unidad;

@RestController
@RequestMapping("/unidades")
public class UnidadController {

	@GetMapping("/getAll")
	public UnidadDaoResponse getAllProductos() {
		UnidadJDBC unidadJDBC = new UnidadJDBC();
		UnidadDaoResponse unidadDaoResponse = new UnidadDaoResponse();
		List<Unidad> listaUnidades = new ArrayList<>();
		try {
			listaUnidades = unidadJDBC.findAll();
			unidadDaoResponse.setCodigo("0");
			unidadDaoResponse.setDescripcion("success");
			unidadDaoResponse.setUnidades(listaUnidades);

		} catch (SQLException e) {
			unidadDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			unidadDaoResponse.setDescripcion(e.getMessage());
		}

		return unidadDaoResponse;
	}
}
