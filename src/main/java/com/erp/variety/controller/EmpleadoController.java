package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.EmpleadoDaoResponse;
import com.erp.variety.jdbc.EmpleadoJDBC;
import com.erp.variety.model.Empleado;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {


	@GetMapping("/getAll")
	public EmpleadoDaoResponse getAllClients() {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		EmpleadoDaoResponse empleadoDaoResponse = new EmpleadoDaoResponse();
		List<Empleado> listaempleados = new ArrayList<>();
		try {
			listaempleados = empleadoJDBC.findAll();
			empleadoDaoResponse.setCodigo("0");
			empleadoDaoResponse.setDescripcion("EXITO");
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
			empleadoDaoResponse.setDescripcion("EXITO");
			empleadoDaoResponse.setEmpleados(listaempleados);
		} catch (SQLException e) {
			empleadoDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			empleadoDaoResponse.setDescripcion(e.getMessage());
		}
		return empleado;
	}
	
	@PostMapping("/saveEmpleado")
	public void saveClient(Empleado empleado) {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		try {
			empleadoJDBC.save(empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/editEmpleado")
	public void editEmpleado(Empleado empleado) {
		EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
		EmpleadoDaoResponse empleadoDaoResponse = new EmpleadoDaoResponse();
		try {
			empleadoJDBC.edit(empleado);
		} catch (SQLException e) {
			empleadoDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			empleadoDaoResponse.setDescripcion(e.getMessage());
			
		}
		
	}
	
	@PostMapping("/deleteEmpleado")
	public void deleteEmpleado(Empleado empleado) {
		
	}
}
