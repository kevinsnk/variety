package com.erp.variety.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.variety.dao.CuentasXCobrarDaoRequest;
import com.erp.variety.dao.CuentasxCobrarDaoResponse;
import com.erp.variety.jdbc.CuentasxCobrarJDBC;
import com.erp.variety.jdbc.PaqueteJDBC;
import com.erp.variety.model.Cobros;
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
			// cuentasxCobrarDaoResponse.setMovimientos(listaCobros);

		} catch (SQLException e) {
			cuentasxCobrarDaoResponse.setCodigo(String.valueOf(e.getErrorCode()));
			cuentasxCobrarDaoResponse.setDescripcion(e.getMessage());
		}

		return cuentasxCobrarDaoResponse;
	}

	@GetMapping("/getMovimientosByClient")
	public CuentasxCobrarDaoResponse getMovimientosByClient(
			@RequestParam(value = "idCliente", required = true) String idCliente) {
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
	public CuentasxCobrarDaoResponse saveCxC(@RequestBody CuentasXCobrarDaoRequest request) {
		CuentasxCobrarJDBC cuentasxCobrarJDBC = new CuentasxCobrarJDBC();
		PaqueteJDBC paqueteJDBC = new PaqueteJDBC();
		CuentasxCobrarDaoResponse cuentasxCobrarDaoResponse = new CuentasxCobrarDaoResponse();
		List<Paquete> listaPaquetesXCliente = new ArrayList<>();
		String codigoRespuesta = "0";
		// idCliente = clientesJDBC.getCorrelativo();
		int idCobro;

		BigDecimal montoTmp = request.getMontoPagado();
		try {
			// Se guarda pago en tabla de cobros
			idCobro = Integer.valueOf(cuentasxCobrarJDBC.getCorrelativo());
			request.setIdCobro(idCobro);
			codigoRespuesta = cuentasxCobrarJDBC.save(request);
			if (codigoRespuesta.equals("0")) {
				listaPaquetesXCliente = paqueteJDBC.getPaquetesXCliente(request.getIdCliente());
				for (Paquete paquete : listaPaquetesXCliente) {
					if (montoTmp.doubleValue() <= paquete.getSaldo().doubleValue()) {
						montoTmp = paquete.getSaldo().subtract(request.getMontoPagado());
						paquete.setSaldo(montoTmp);
						codigoRespuesta = paqueteJDBC.edit(paquete);
						break;
					} else {
						montoTmp = request.getMontoPagado().subtract(paquete.getSaldo());
						paquete.setSaldo(BigDecimal.ZERO);
						codigoRespuesta = paqueteJDBC.edit(paquete);
					}
				}
				cuentasxCobrarDaoResponse.setDescripcion("Registro guardado exitosamente");
			} else {
				cuentasxCobrarDaoResponse.setDescripcion("Error al querer guardar el cobro en la tabla");
			}
			cuentasxCobrarDaoResponse.setCodigo(codigoRespuesta);
		} catch (Exception e) {
			e.printStackTrace();
			cuentasxCobrarDaoResponse.setCodigo(codigoRespuesta);
			cuentasxCobrarDaoResponse.setDescripcion(e.getMessage());
		}
		return cuentasxCobrarDaoResponse;
	}
}
