package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.Sub_grupo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Sub_grupo entity.
 */
public interface Sub_grupoSearchRepository extends ElasticsearchRepository<Sub_grupo, Long> {
}
