/**
 * 
 */
package com.example.taller.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 
 */
@Data
public class BicicletaDTO {

	private String color;
	
	@NotNull
	@Size(min = 5, max = 5)
	private String numSerie;
	
	public Bicicleta toBicicleta() {
		return new Bicicleta(color, Integer.valueOf(numSerie).intValue());
	}
}
