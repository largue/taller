/**
 * 
 */
package com.example.taller.model;

import javax.validation.constraints.NotNull;

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

	@Id
	public String id;
	
	private String color;
	
	@Indexed(unique = true)
	@NotNull
	private int numSerie;
	
	private String estadoReparacion;
}
