package com.sth.gpweb.repository;

import com.sth.gpweb.domain.ClassProduto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ClassProduto entity.
 */
@SuppressWarnings("unused")
public interface ClassProdutoRepository extends JpaRepository<ClassProduto,Long> {

	//Find by name, used by select2 on product
	Page<ClassProduto> findByDsClassProdutoStartingWithOrderByDsClassProdutoAsc(String descricao, Pageable pageable);
		
}