package com.sth.gpweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sth.gpweb.domain.Produto;

/**
 * Spring Data JPA repository for the Produto entity.
 */
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
	
	//Verify if nmProduto already exists on database before insert	
	@Query("SELECT g.nmProduto FROM Produto g where g.nmProduto = :nmProduto") 
	String findNmProdutoExists(@Param("nmProduto") String nmProduto);

}
