package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.Grupo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Grupo entity.
 */
public interface GrupoSearchRepository extends ElasticsearchRepository<Grupo, Long> {
}
