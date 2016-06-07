package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.Promocao;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Promocao entity.
 */
public interface PromocaoSearchRepository extends ElasticsearchRepository<Promocao, Long> {
}
