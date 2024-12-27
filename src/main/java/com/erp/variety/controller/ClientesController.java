package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.ClientesDaoResponse;
import com.erp.variety.jdbc.ClientesJDBC;
import com.erp.variety.model.Clientes;

@RestController
@RequestMapping("/clients")
public class ClientesController {

	@GetMapping("/getAll")
	public ClientesDaoResponse getAllClients() {
		ClientesJDBC clientesJDBC = new ClientesJDBC();
		ClientesDaoResponse clientesDaoResponse = new ClientesDaoResponse();
		List<Clientes> listaClientes = new ArrayList<>();
		try {
			listaClientes = clientesJDBC.findAll();
			clientesDaoResponse.setCodigo("0");
			clientesDaoResponse.setDescripcion("EXITO");
			clientesDaoResponse.setClientes(listaClientes);
		} catch (SQLException e) {
			clientesDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			clientesDaoResponse.setDescripcion(e.getMessage());
		}
		
		return clientesDaoResponse;
	}
	
	@GetMapping("/getClient")
	public Clientes getClient() throws SQLException {
		ClientesJDBC clientesJDBC = new ClientesJDBC();
		Clientes clientes = new Clientes();
		List<Clientes> listaClientes = new ArrayList<>();
		ClientesDaoResponse clientesDaoResponse = new ClientesDaoResponse();
		try {
			clientes = clientesJDBC.getRecord(clientes);
			if(clientes != null) {
				listaClientes.add(clientes);
			}
			clientesDaoResponse.setCodigo("0");
			clientesDaoResponse.setDescripcion("EXITO");
			clientesDaoResponse.setClientes(listaClientes);
		} catch (SQLException e) {
			clientesDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			clientesDaoResponse.setDescripcion(e.getMessage());
		}
		return clientes;
	}
	
	@PostMapping("/saveClient")
	public void saveClient(Clientes cliente) {
		ClientesJDBC clientesJDBC = new ClientesJDBC();
		try {
			clientesJDBC.save(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/editClient")
	public void editClient(Clientes cliente) {
		
	}
	
	@PostMapping("/deleteClient")
	public void deleteClient(Clientes cliente) {
		
	}
	
}
