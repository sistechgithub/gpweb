package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.Filial;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Filial entity.
 */
public interface FilialSearchRepository extends ElasticsearchRepository<Filial, Long> {
}
