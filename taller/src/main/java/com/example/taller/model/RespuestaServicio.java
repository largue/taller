/**
 * 
 */
package com.example.taller.model;

import lombok.Data;

/**
 * 
 */
@Data
public class RespuestaServicio {

	public RespuestaServicio(String codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	private String codigo;
	
	private String mensaje;
}
