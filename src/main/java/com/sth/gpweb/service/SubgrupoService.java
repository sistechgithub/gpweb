package com.sth.gpweb.service;

import com.sth.gpweb.domain.Subgrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Subgrupo.
 */
public interface SubgrupoService {

    /**
     * Save a subgrupo.
     * 
     * @param subgrupo the entity to save
     * @return the persisted entity
     */
    Subgrupo save(Subgrupo subgrupo);

    /**
     *  Get all the subgrupos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Subgrupo> findAll(Pageable pageable);

    /**
     *  Get the "id" subgrupo.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Subgrupo findOne(Long id);

    /**
     *  Delete the "id" subgrupo.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the subgrupo corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<Subgrupo> search(String query, Pageable pageable);
}
