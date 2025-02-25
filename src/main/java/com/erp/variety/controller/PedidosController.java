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
@RequestMapping("/pedidos")
public class PedidosController {

	@GetMapping("/getAll")
	public PedidosDaoResponse getAllPedidos() {
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

	@GetMapping("/getPedido")
	public PedidosDaoResponse getPedido(@RequestBody Integer idPedido) throws SQLException {
		PedidosJDBC pedidosJDBC = new PedidosJDBC();
		Paquete pedido = new Paquete();
		List<Paquete> listaPedidos = new ArrayList<>();
		PedidosDaoResponse pedidosDaoResponse = new PedidosDaoResponse();
		try {
			pedido = pedidosJDBC.getRecord(idPedido);
			if (pedido != null) {
				listaPedidos.add(pedido);
			}
			pedidosDaoResponse.setCodigo("0");
			pedidosDaoResponse.setDescripcion("success");
			pedidosDaoResponse.setPedidos(listaPedidos);

		} catch (SQLException e) {
			pedidosDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			pedidosDaoResponse.setDescripcion(e.getMessage());
		}
		return pedidosDaoResponse;
	}

	@PostMapping("/savePedido")
	public PedidosDaoResponse savePedido(@RequestBody PedidosDaoRequest pedido) {
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

	@PostMapping("/editPedido")
	public PedidosDaoResponse editPedido(@RequestBody PedidosDaoRequest pedido) {
		PedidosJDBC pedidosJDBC = new PedidosJDBC();
		PedidosDaoResponse pedidosDaoResponse = new PedidosDaoResponse();
		String codigoRespuesta = "0";
		try {
			codigoRespuesta = pedidosJDBC.edit(pedido);
			if (codigoRespuesta.equals("0")) {
				pedidosDaoResponse.setDescripcion("Registro editado exitosamente");
			} else {
				pedidosDaoResponse.setDescripcion("Error al querer guardar los cambios del pedido en la tabla");
			}
			pedidosDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			pedidosDaoResponse.setCodigo(codigoRespuesta);
			pedidosDaoResponse.setDescripcion(e.getMessage());

		}
		return pedidosDaoResponse;
	}

	@PostMapping("/deletePedido")
	public PedidosDaoResponse deletePedido(@RequestBody PedidosDaoRequest pedido) {
		PedidosJDBC pedidosJDBC = new PedidosJDBC();
		PedidosDaoResponse pedidosDaoResponse = new PedidosDaoResponse();
		String codigoRespuesta = "0";
		try {
			if (pedido != null && !pedido.getIdPaquete().equals("")) {
				codigoRespuesta = pedidosJDBC.delete(pedido);
				if (codigoRespuesta.equals("0")) {
					pedidosDaoResponse.setDescripcion("Registro eliminado exitosamente");
				} else {
					pedidosDaoResponse.setDescripcion("Error al querer eliminar el pedido en la tabla");
				}
				pedidosDaoResponse.setCodigo(codigoRespuesta);
			} else {
				pedidosDaoResponse.setCodigo("1");
				pedidosDaoResponse.setDescripcion("Debe de mandar un código de pedido valido.");
			}
		} catch (Exception e) {
			pedidosDaoResponse.setCodigo(codigoRespuesta);
			pedidosDaoResponse.setDescripcion(e.getMessage());
			e.printStackTrace();
		}
		return pedidosDaoResponse;
	}
}
