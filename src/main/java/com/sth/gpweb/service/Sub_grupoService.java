package com.sth.gpweb.service;

import com.sth.gpweb.domain.Sub_grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Sub_grupo.
 */
public interface Sub_grupoService {

    /**
     * Save a sub_grupo.
     * 
     * @param sub_grupo the entity to save
     * @return the persisted entity
     */
    Sub_grupo save(Sub_grupo sub_grupo);

    /**
     *  Get all the sub_grupos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Sub_grupo> findAll(Pageable pageable);

    /**
     *  Get the "id" sub_grupo.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Sub_grupo findOne(Long id);

    /**
     *  Delete the "id" sub_grupo.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the sub_grupo corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<Sub_grupo> search(String query, Pageable pageable);
}
