package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Subgrupo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Subgrupo entity.
 */
@SuppressWarnings("unused")
public interface SubgrupoRepository extends JpaRepository<Subgrupo,Long> {

}
