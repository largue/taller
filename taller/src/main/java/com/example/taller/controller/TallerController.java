/**
 * 
 */
package com.example.taller.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taller.model.Bicicleta;
import com.example.taller.model.RespuestaServicio;
import com.example.taller.servicios.TallerService;

@RestController
@RequestMapping("/taller")
public class TallerController {
	
	@Autowired
	private TallerService tallerService;
	
	@PostMapping("/entregarBiciAlTaller")
	public RespuestaServicio entregarBiciAlTaller(@Valid @RequestBody Bicicleta bici, BindingResult bindingResult) {
		/*boolean altaBici = validarRequest(bici);
		
		if (altaBici) {
			return tallerService.entregarBiciAlTaller(bici);
		} else {
			return new RespuestaServicio("KO", "KO");
		}*/
		
		if (bindingResult.hasErrors()) {
			return new RespuestaServicio("KO", "KO");
        } else {
        	return tallerService.entregarBiciAlTaller(bici);
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
	
	private boolean validarRequest(Bicicleta bici) {
		boolean respuesta = true;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();


		Set<ConstraintViolation<Bicicleta>> violations = validator.validate(bici);
		
		if (!violations.isEmpty()) {
			respuesta = false;
		}
		
		return respuesta;
	}
}
