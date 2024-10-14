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

	public RespuestaServicio(String mensaje) {
		this.codigo = mensaje.substring(0, 2);
		this.mensaje = mensaje.substring(4, mensaje.length() - 1);
	}

	private String codigo;
	
	private String mensaje;
}
