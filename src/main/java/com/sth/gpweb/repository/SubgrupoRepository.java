package com.sth.gpweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sth.gpweb.domain.Subgrupo;

/**
 * Spring Data JPA repository for the Subgrupo entity.
 */
public interface SubgrupoRepository extends JpaRepository<Subgrupo,Long> {
	
	//Find by name, used by select2 on product
	Page<Subgrupo> findByNmSubgrupoStartingWithOrderByNmSubgrupoAsc(String descricao, Pageable pageable);

}
