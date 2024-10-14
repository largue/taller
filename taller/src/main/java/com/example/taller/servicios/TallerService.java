/**
 * 
 */
package com.example.taller.servicios;

import com.example.taller.model.Bicicleta;
import com.example.taller.model.RespuestaServicio;

/**
 * 
 */
public interface TallerService {
	
	public RespuestaServicio entregarBiciAlTaller(Bicicleta bici);
	
	public RespuestaServicio recogerBiciCliente(int numSerie);
	
	public RespuestaServicio actualizarEstadoReparacion(int numSerie, String estadoReparacion);
}
