/**
 * 
 */
package com.example.taller.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.taller.model.Bicicleta;

/**
 * 
 */
@RepositoryRestResource(collectionResourceRel = "bicicleta", path = "bicicleta")
public interface BicicletaRepository extends MongoRepository<Bicicleta, String> {

	public Bicicleta findByNumSerie(@Param("numSerie") int numSerie);
}