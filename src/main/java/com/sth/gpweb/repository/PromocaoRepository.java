package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Promocao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Promocao entity.
 */
@SuppressWarnings("unused")
public interface PromocaoRepository extends JpaRepository<Promocao,Long> {

}
