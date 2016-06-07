package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.Marca;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Marca entity.
 */
public interface MarcaSearchRepository extends ElasticsearchRepository<Marca, Long> {
}
