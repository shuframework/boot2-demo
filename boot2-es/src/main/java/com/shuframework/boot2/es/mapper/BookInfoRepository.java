package com.shuframework.boot2.es.mapper;

import com.shuframework.boot2.es.model.BookInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ElasticsearchRepository的依赖顺序
 *  ElasticsearchRepository --> ElasticsearchCrudRepository --> PagingAndSortingRepository --> CrudRepository
 *
 * @author shuheng
 */
public interface BookInfoRepository extends ElasticsearchRepository<BookInfo, Long> {
}
