package com.erp.variety.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.CuentasxCobrarDaoResponse;
import com.erp.variety.dao.PedidosDaoRequest;
import com.erp.variety.dao.PedidosDaoResponse;
import com.erp.variety.jdbc.CuentasxCobrarJDBC;
import com.erp.variety.jdbc.PedidosJDBC;
import com.erp.variety.model.Movimientos;
import com.erp.variety.model.Paquete;

@RestController
@RequestMapping("/cxc")
public class CuentasxCobrarController {

	@GetMapping("/getAll")
	public CuentasxCobrarDaoResponse getAllCxC() {
		CuentasxCobrarJDBC cuentasxCobrarJDBC = new CuentasxCobrarJDBC();
		CuentasxCobrarDaoResponse cuentasxCobrarDaoResponse = new CuentasxCobrarDaoResponse();
		List<Paquete> listaCobros = new ArrayList<>();
		try {
			listaCobros = cuentasxCobrarJDBC.findAll();
			cuentasxCobrarDaoResponse.setCodigo("0");
			cuentasxCobrarDaoResponse.setDescripcion("success");
			//cuentasxCobrarDaoResponse.setMovimientos(listaCobros);

		} catch (SQLException e) {
			cuentasxCobrarDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			cuentasxCobrarDaoResponse.setDescripcion(e.getMessage());
		}

		return cuentasxCobrarDaoResponse;
	}
	
	@GetMapping("/getMovimientosByClient")
	public CuentasxCobrarDaoResponse getMovimientosByClient(@RequestParam(value = "idCliente", required = true) String idCliente) {
		CuentasxCobrarJDBC cuentasxCobrarJDBC = new CuentasxCobrarJDBC();
		CuentasxCobrarDaoResponse cuentasxCobrarDaoResponse = new CuentasxCobrarDaoResponse();
		List<Movimientos> listaMovimientos = new ArrayList<>();
		try {
			listaMovimientos = cuentasxCobrarJDBC.getMovimientosByClient(idCliente);
			cuentasxCobrarDaoResponse.setCodigo("0");
			cuentasxCobrarDaoResponse.setDescripcion("success");
			cuentasxCobrarDaoResponse.setMovimientos(listaMovimientos);

		} catch (SQLException e) {
			cuentasxCobrarDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			cuentasxCobrarDaoResponse.setDescripcion(e.getMessage());
		}

		return cuentasxCobrarDaoResponse;
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
