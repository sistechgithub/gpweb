package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.ClassProduto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the ClassProduto entity.
 */
public interface ClassProdutoSearchRepository extends ElasticsearchRepository<ClassProduto, Long> {
}
