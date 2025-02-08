package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.PedidosDaoRequest;
import com.erp.variety.dao.PedidosDaoResponse;
import com.erp.variety.jdbc.PedidosJDBC;
import com.erp.variety.model.Paquete;

@RestController
@RequestMapping("/cxc")
public class CuentasxCobrarController {

	@GetMapping("/getAll")
	public PedidosDaoResponse getAllCxC() {
		PedidosJDBC pedidosJDBC = new PedidosJDBC();
		PedidosDaoResponse pedidosDaoResponse = new PedidosDaoResponse();
		List<Paquete> listaPedidos = new ArrayList<>();
		try {
			listaPedidos = pedidosJDBC.findAll();
			pedidosDaoResponse.setCodigo("0");
			pedidosDaoResponse.setDescripcion("success");
			pedidosDaoResponse.setPedidos(listaPedidos);

		} catch (SQLException e) {
			pedidosDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			pedidosDaoResponse.setDescripcion(e.getMessage());
		}

		return pedidosDaoResponse;
	}
	
	@GetMapping("/getMovimientosByClient")
	public PedidosDaoResponse getMovimientosByClient(@RequestBody String idCliente) {
		PedidosJDBC pedidosJDBC = new PedidosJDBC();
		PedidosDaoResponse pedidosDaoResponse = new PedidosDaoResponse();
		List<Paquete> listaPedidos = new ArrayList<>();
		try {
			listaPedidos = pedidosJDBC.findAll();
			pedidosDaoResponse.setCodigo("0");
			pedidosDaoResponse.setDescripcion("success");
			pedidosDaoResponse.setPedidos(listaPedidos);

		} catch (SQLException e) {
			pedidosDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			pedidosDaoResponse.setDescripcion(e.getMessage());
		}

		return pedidosDaoResponse;
	}

	@PostMapping("/saveCXC")
	public PedidosDaoResponse saveCxC(@RequestBody PedidosDaoRequest pedido) {
		PedidosJDBC pedidosJDBC = new PedidosJDBC();
		PedidosDaoResponse pedidosDaoResponse = new PedidosDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = pedidosJDBC.edit(pedido);
			if (codigoRespuesta.equals("0")) {
				pedidosDaoResponse.setDescripcion("Registro guardado exitosamente");
			} else {
				pedidosDaoResponse.setDescripcion("Error al querer guardar el nuevo pedido en la tabla");
			}
			pedidosDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			pedidosDaoResponse.setCodigo(codigoRespuesta);
			pedidosDaoResponse.setDescripcion(e.getMessage());
		}
		return pedidosDaoResponse;
	}
}
