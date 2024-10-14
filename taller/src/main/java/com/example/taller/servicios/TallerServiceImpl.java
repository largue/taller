/**
 * 
 */
package com.example.taller.servicios;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.taller.model.Bicicleta;
import com.example.taller.model.RespuestaServicio;
import com.example.taller.repository.BicicletaRepository;
import com.example.taller.utils.EstadosReparacion;
import com.example.taller.utils.StaticBundle;

/**
 * 
 */
@Service
public class TallerServiceImpl implements TallerService {
	
	Logger logger = LoggerFactory.getLogger(TallerServiceImpl.class);
	
	@Autowired
	private BicicletaRepository bicicletaRepository;
	
	public RespuestaServicio entregarBiciAlTaller(Bicicleta bici) {
		String mensaje = "";
		Bicicleta biciAux = null;
		boolean altaBici = true;
		
		try {
			List<Bicicleta> listadoBicisTaller = bicicletaRepository.findAll();
			
			if (!CollectionUtils.isEmpty(listadoBicisTaller) && listadoBicisTaller.size() >= 5) {
				altaBici = false;
			}
			
			if (altaBici) {
				biciAux = bicicletaRepository.insert(bici);
				
				if (biciAux != null) {
					mensaje = StaticBundle.getInstance().getString("alta.bici.exito");
				}
			} else {
				mensaje = StaticBundle.getInstance().getString("alta.bici.taller.lleno");
			}
		} catch (DuplicateKeyException mwe) {
			mensaje = StaticBundle.getInstance().getString("alta.bici.error");
			logger.debug(mensaje, mwe);
		}
		
		return new RespuestaServicio(mensaje);
	}
	
	public RespuestaServicio recogerBiciCliente(int numSerie) {
		String mensaje = "";
		Bicicleta biciAux = null;
		
		biciAux = buscarBicicleta(numSerie);
		
		if (biciAux == null) {
			mensaje = StaticBundle.getInstance().getString("bici.no.encontrada");
		} else {
			if (EstadosReparacion.FINALIZADA.getCodigo().equalsIgnoreCase(biciAux.getEstadoReparacion())) {
				bicicletaRepository.delete(biciAux);
				
				logger.debug(StaticBundle.getInstance().getString("info.eliminar.bici.db").replace("{0}", String.valueOf(numSerie)));
				mensaje = StaticBundle.getInstance().getString("entrega.bici.exito");
			} else {
				mensaje = StaticBundle.getInstance().getString("entrega.bici.error");
			}
		}
		
		return new RespuestaServicio(mensaje);
	}

	@Override
	public RespuestaServicio actualizarEstadoReparacion(int numSerie, String estadoReparacion) {
		String mensaje = "";
		Bicicleta biciAux = null;
		
		biciAux = buscarBicicleta(numSerie);
		
		if (biciAux == null) {
			mensaje = StaticBundle.getInstance().getString("bici.no.encontrada");
		} else {
			biciAux.setEstadoReparacion(estadoReparacion);
			bicicletaRepository.save(biciAux);
			
			mensaje = StaticBundle.getInstance().getString("actualizar.estado.reparacion");
		}
		
		return new RespuestaServicio(mensaje);
	}
	
	private Bicicleta buscarBicicleta(int numSerie) {
		return bicicletaRepository.findByNumSerie(numSerie);
	}
}
