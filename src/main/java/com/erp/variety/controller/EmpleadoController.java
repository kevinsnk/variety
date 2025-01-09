package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.EmpleadoDaoResponse;
import com.erp.variety.jdbc.EmpleadoJDBC;
import com.erp.variety.model.Empleado;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {


	@GetMapping("/getAll")
	public EmpleadoDaoResponse getAllClients() {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		EmpleadoDaoResponse empleadoDaoResponse = new EmpleadoDaoResponse();
		List<Empleado> listaempleados = new ArrayList<>();
		try {
			listaempleados = empleadoJDBC.findAll();
			empleadoDaoResponse.setCodigo("0");
			empleadoDaoResponse.setDescripcion("success");
			empleadoDaoResponse.setEmpleados(listaempleados);
		} catch (SQLException e) {
			empleadoDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			empleadoDaoResponse.setDescripcion(e.getMessage());
		}
		
		return empleadoDaoResponse;
	}
	
	@GetMapping("/getEmpleado")
	public Empleado getEmpleado() throws SQLException {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		Empleado empleado = new Empleado();
		List<Empleado> listaempleados = new ArrayList<>();
		EmpleadoDaoResponse empleadoDaoResponse = new EmpleadoDaoResponse();
		try {
			empleado = empleadoJDBC.getRecord(empleado);
			if(empleado != null) {
				listaempleados.add(empleado);
			}
			empleadoDaoResponse.setCodigo("0");
			empleadoDaoResponse.setDescripcion("success");
			empleadoDaoResponse.setEmpleados(listaempleados);
		} catch (SQLException e) {
			empleadoDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			empleadoDaoResponse.setDescripcion(e.getMessage());
		}
		return empleado;
	}
	
	@PostMapping("/saveEmpleado")
	public EmpleadoDaoResponse saveClient(@RequestBody Empleado empleado) {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		EmpleadoDaoResponse empleadoDaoResponse = new EmpleadoDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = empleadoJDBC.save(empleado);
			if(codigoRespuesta.equals("0")) {
				empleadoDaoResponse.setDescripcion("success");
			}else {
				empleadoDaoResponse.setDescripcion("Error al querer guardar el nuevo cliente en la tabla");
			}
			empleadoDaoResponse.setCodigo(codigoRespuesta);
		} catch (SQLException e) {
			e.printStackTrace();
			empleadoDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			empleadoDaoResponse.setDescripcion(e.getMessage());
		}
		return empleadoDaoResponse;
	}
	
	@PostMapping("/editEmpleado")
	public EmpleadoDaoResponse editEmpleado(@RequestBody Empleado empleado) {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		EmpleadoDaoResponse empleadoDaoResponse = new EmpleadoDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = empleadoJDBC.edit(empleado);
			if(codigoRespuesta.equals("0")) {
				empleadoDaoResponse.setDescripcion("success");
			}else {
				empleadoDaoResponse.setDescripcion("Error al querer guardar el nuevo cliente en la tabla");
			}
			empleadoDaoResponse.setCodigo(codigoRespuesta);
		} catch (SQLException e) {
			empleadoDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			empleadoDaoResponse.setDescripcion(e.getMessage());
			
		}
		return empleadoDaoResponse;
	}
	
	@PostMapping("/deleteEmpleado")
	public void deleteEmpleado(@RequestBody Empleado empleado) {
		
	}
}
