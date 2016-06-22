package com.sth.gpweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sth.gpweb.domain.Marca;

/**
 * Spring Data JPA repository for the Marca entity.
 */
@SuppressWarnings("unused")
public interface MarcaRepository extends JpaRepository<Marca,Long> {

	//Find by name, used by select2 on product
	Page<Marca> findByNmFabricanteStartingWithOrderByNmFabricanteAsc(String descricao, Pageable pageable);
	
}
