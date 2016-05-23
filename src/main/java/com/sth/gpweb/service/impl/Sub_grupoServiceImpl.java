package com.sth.gpweb.service.impl;

import com.sth.gpweb.service.Sub_grupoService;
import com.sth.gpweb.domain.Sub_grupo;
import com.sth.gpweb.repository.Sub_grupoRepository;
import com.sth.gpweb.repository.search.Sub_grupoSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Sub_grupo.
 */
@Service
@Transactional
public class Sub_grupoServiceImpl implements Sub_grupoService{

    private final Logger log = LoggerFactory.getLogger(Sub_grupoServiceImpl.class);
    
    @Inject
    private Sub_grupoRepository sub_grupoRepository;
    
    @Inject
    private Sub_grupoSearchRepository sub_grupoSearchRepository;
    
    /**
     * Save a sub_grupo.
     * 
     * @param sub_grupo the entity to save
     * @return the persisted entity
     */
    public Sub_grupo save(Sub_grupo sub_grupo) {
        log.debug("Request to save Sub_grupo : {}", sub_grupo);
        Sub_grupo result = sub_grupoRepository.save(sub_grupo);
        sub_grupoSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the sub_grupos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Sub_grupo> findAll(Pageable pageable) {
        log.debug("Request to get all Sub_grupos");
        Page<Sub_grupo> result = sub_grupoRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one sub_grupo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Sub_grupo findOne(Long id) {
        log.debug("Request to get Sub_grupo : {}", id);
        Sub_grupo sub_grupo = sub_grupoRepository.findOne(id);
        return sub_grupo;
    }

    /**
     *  Delete the  sub_grupo by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Sub_grupo : {}", id);
        sub_grupoRepository.delete(id);
        sub_grupoSearchRepository.delete(id);
    }

    /**
     * Search for the sub_grupo corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Sub_grupo> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Sub_grupos for query {}", query);
        return sub_grupoSearchRepository.search(queryStringQuery(query), pageable);
    }
}
