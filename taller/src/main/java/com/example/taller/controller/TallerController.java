/**
 * 
 */
package com.example.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller.model.BicicletaDTO;
import com.example.taller.model.RespuestaServicio;
import com.example.taller.servicios.TallerService;
import com.example.taller.utils.StaticBundle;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/taller")
public class TallerController {
	
	@Autowired
	private TallerService tallerService;
	
	@PostMapping("/entregarBiciAlTaller")
	public RespuestaServicio entregarBiciAlTaller(@Valid @RequestBody BicicletaDTO request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String mensaje = StaticBundle.getInstance().getString("error.campoentrada.numserie");
		
			return new RespuestaServicio(mensaje);
        } else {
        	return tallerService.entregarBiciAlTaller(request.toBicicleta());
        }
	}
	
	@GetMapping("/recogerBiciCliente")
	public RespuestaServicio recogerBiciCliente(@RequestParam("numSerie") int numSerie) {
		return tallerService.recogerBiciCliente(numSerie);
	}
	
	@GetMapping("/actualizarEstadoReparacion")
	public RespuestaServicio actualizarEstadoReparacion(@RequestParam("numSerie") int numSerie,
			@RequestParam("estadoReparacion") String estadoReparacion) {
		return tallerService.actualizarEstadoReparacion(numSerie, estadoReparacion);
	}
}
