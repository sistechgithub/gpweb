package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Grupo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Grupo entity.
 */
@SuppressWarnings("unused")
public interface GrupoRepository extends JpaRepository<Grupo,Long> {
	
	//Verify if nmGrupo already exits on database before insert	
	@Query("SELECT g.nmGrupo FROM Grupo g where g.nmGrupo = :nmGrupo") 
	String findNmGrupoExists(@Param("nmGrupo") String nmGrupo);

}
