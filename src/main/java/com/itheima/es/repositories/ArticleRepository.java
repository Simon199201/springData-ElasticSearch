package com.itheima.es.repositories;

import com.itheima.es.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}
