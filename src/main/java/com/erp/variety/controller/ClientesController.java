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
	public ClientesDaoResponse getClient() throws SQLException {
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
		return clientesDaoResponse;
	}
	
	@PostMapping("/saveClient")
	public ClientesDaoResponse saveClient(Clientes cliente) {
		ClientesJDBC clientesJDBC = new ClientesJDBC();
		ClientesDaoResponse clientesDaoResponse = new ClientesDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = clientesJDBC.save(cliente);
			clientesDaoResponse.setCodigo(codigoRespuesta);
			clientesDaoResponse.setDescripcion("EXITO");
		} catch (Exception e) {
			e.printStackTrace();
			clientesDaoResponse.setCodigo(codigoRespuesta);
			clientesDaoResponse.setDescripcion(e.getMessage());
		}
		
		return clientesDaoResponse;
	}
	
	@PostMapping("/editClient")
	public ClientesDaoResponse editClient(Clientes cliente) {
		ClientesJDBC clientesJDBC = new ClientesJDBC();
		ClientesDaoResponse clientesDaoResponse = new ClientesDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = clientesJDBC.edit(cliente);
			clientesDaoResponse.setCodigo(codigoRespuesta);
			clientesDaoResponse.setDescripcion("EXITO");
		} catch (Exception e) {
			clientesDaoResponse.setCodigo(codigoRespuesta);
			clientesDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return clientesDaoResponse;
	}
	
	@PostMapping("/deleteClient")
	public ClientesDaoResponse deleteClient(String cliente) {
		ClientesJDBC clientesJDBC = new ClientesJDBC();
		ClientesDaoResponse clientesDaoResponse = new ClientesDaoResponse();
		String codigoRespuesta = "0";
		try {
			if(cliente != null && !cliente.trim().equals("")) {
				codigoRespuesta = clientesJDBC.delete(cliente);
				clientesDaoResponse.setCodigo(codigoRespuesta);
				clientesDaoResponse.setDescripcion("EXITO");
			}else {
				clientesDaoResponse.setCodigo("1");
				clientesDaoResponse.setDescripcion("Debe de mandar un código de cliente valido.");
			}
		} catch (SQLException e) {
			clientesDaoResponse.setCodigo(codigoRespuesta);
			clientesDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return clientesDaoResponse;
	}
	
}
