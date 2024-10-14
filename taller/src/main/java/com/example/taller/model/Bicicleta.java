/**
 * 
 */
package com.example.taller.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * 
 */
@Document(collection="bicicleta")
@Data
public class Bicicleta {
	
	public Bicicleta(String color, int numSerie) {
		this.color = color;
		this.numSerie = numSerie;
		this.estadoReparacion = "0";
	}

	@Id
	public String id;
	
	private String color;
	
	@Indexed(unique = true)
	private int numSerie;
	
	private String estadoReparacion;
}
