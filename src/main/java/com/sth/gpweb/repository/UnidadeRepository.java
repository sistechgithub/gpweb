package com.sth.gpweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sth.gpweb.domain.Unidade;

/**
 * Spring Data JPA repository for the Unidade entity.
 */
@SuppressWarnings("unused")
public interface UnidadeRepository extends JpaRepository<Unidade,Long> {

	//Find by name, used by select2 on product
	Page<Unidade> findByDsUnidadeStartingWithOrderByDsUnidadeAsc(String descricao, Pageable pageable);
	
}
