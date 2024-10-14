/**
 * 
 */
package com.example.taller.utils;

/**
 * 
 */
public enum EstadosReparacion {

	PENDIENTE("0"),
	ENPROCESO("2"),
	FINALIZADA("1");

	private String codigo;
	
	private EstadosReparacion(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
