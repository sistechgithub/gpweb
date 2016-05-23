package com.sth.gpweb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sth.gpweb.domain.Sub_grupo;
import com.sth.gpweb.service.Sub_grupoService;
import com.sth.gpweb.web.rest.util.HeaderUtil;
import com.sth.gpweb.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Sub_grupo.
 */
@RestController
@RequestMapping("/api")
public class Sub_grupoResource {

    private final Logger log = LoggerFactory.getLogger(Sub_grupoResource.class);
        
    @Inject
    private Sub_grupoService sub_grupoService;
    
    /**
     * POST  /sub-grupos : Create a new sub_grupo.
     *
     * @param sub_grupo the sub_grupo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sub_grupo, or with status 400 (Bad Request) if the sub_grupo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sub-grupos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sub_grupo> createSub_grupo(@Valid @RequestBody Sub_grupo sub_grupo) throws URISyntaxException {
        log.debug("REST request to save Sub_grupo : {}", sub_grupo);
        if (sub_grupo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sub_grupo", "idexists", "A new sub_grupo cannot already have an ID")).body(null);
        }
        Sub_grupo result = sub_grupoService.save(sub_grupo);
        return ResponseEntity.created(new URI("/api/sub-grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sub_grupo", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-grupos : Updates an existing sub_grupo.
     *
     * @param sub_grupo the sub_grupo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sub_grupo,
     * or with status 400 (Bad Request) if the sub_grupo is not valid,
     * or with status 500 (Internal Server Error) if the sub_grupo couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sub-grupos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sub_grupo> updateSub_grupo(@Valid @RequestBody Sub_grupo sub_grupo) throws URISyntaxException {
        log.debug("REST request to update Sub_grupo : {}", sub_grupo);
        if (sub_grupo.getId() == null) {
            return createSub_grupo(sub_grupo);
        }
        Sub_grupo result = sub_grupoService.save(sub_grupo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sub_grupo", sub_grupo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-grupos : get all the sub_grupos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sub_grupos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/sub-grupos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Sub_grupo>> getAllSub_grupos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Sub_grupos");
        Page<Sub_grupo> page = sub_grupoService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-grupos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sub-grupos/:id : get the "id" sub_grupo.
     *
     * @param id the id of the sub_grupo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sub_grupo, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/sub-grupos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Sub_grupo> getSub_grupo(@PathVariable Long id) {
        log.debug("REST request to get Sub_grupo : {}", id);
        Sub_grupo sub_grupo = sub_grupoService.findOne(id);
        return Optional.ofNullable(sub_grupo)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sub-grupos/:id : delete the "id" sub_grupo.
     *
     * @param id the id of the sub_grupo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/sub-grupos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSub_grupo(@PathVariable Long id) {
        log.debug("REST request to delete Sub_grupo : {}", id);
        sub_grupoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sub_grupo", id.toString())).build();
    }

    /**
     * SEARCH  /_search/sub-grupos?query=:query : search for the sub_grupo corresponding
     * to the query.
     *
     * @param query the query of the sub_grupo search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/sub-grupos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Sub_grupo>> searchSub_grupos(@RequestParam String query, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of Sub_grupos for query {}", query);
        Page<Sub_grupo> page = sub_grupoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sub-grupos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
