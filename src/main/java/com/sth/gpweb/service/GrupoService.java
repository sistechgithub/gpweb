package com.sth.gpweb.service;

import com.sth.gpweb.domain.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Grupo.
 */
public interface GrupoService {

    /**
     * Save a grupo.
     * 
     * @param grupo the entity to save
     * @return the persisted entity
     */
    Grupo save(Grupo grupo);

    /**
     *  Get all the grupos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Grupo> findAll(Pageable pageable);

    /**
     *  Get the "id" grupo.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Grupo findOne(Long id);

    /**
     *  Delete the "id" grupo.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the grupo corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<Grupo> search(String query, Pageable pageable);
    
    /**
     * Search for the nmGrupo already exists.
     * 
     *  @param query the nmGrupo
     *  @return the list of entities
     */
    String findNmGrupoExists(String nmGrupo);
    
    /**
     * Search for the produto corresponding to the query name.
     * 
     *  @param description query for the name
     *  @return the list of entities
     */
    Page<Grupo> findByNomeStartingWithOrderByNomeAsc(String descricao, Pageable pageable);
}
