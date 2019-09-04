package com.shuframework.boot2.es.service.impl;

import com.google.common.collect.Lists;
import com.shuframework.boot2.es.mapper.BookInfoRepository;
import com.shuframework.boot2.es.model.BookInfo;
import com.shuframework.boot2.es.service.BookInfoService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author shuheng
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoRepository bookInfoRepository;


    @Override
    public boolean save(BookInfo bookInfo) {
        BookInfo result = bookInfoRepository.save(bookInfo);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<BookInfo> search(String searchContent) {
        QueryStringQueryBuilder build = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询语句："+build.toString());
        return searchByQuery(build);
    }

    @Override
    public List<BookInfo> searchByWeight(String searchContent) {
        FunctionScoreQueryBuilder build = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", searchContent));
        System.out.println("查询语句："+build.toString());
        return searchByQuery(build);
    }

    private List<BookInfo> searchByQuery(QueryBuilder build) {
        Iterable<BookInfo> searchResult = bookInfoRepository.search(build);
//        // 等价于下面的方法
//        Iterator<BookInfo> iterator = searchResult.iterator();
//        List<BookInfo> list = new ArrayList<>();
//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
        List<BookInfo> list = Lists.newArrayList(searchResult);
        return list;
    }

    @Override
    public List<BookInfo> search4Page(Integer pageIndex, Integer pageSize, String searchContent) {
        //查询条件
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        //分页对象
        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
        //组装的最后条件
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的searchQuery:" + searchQuery.toString());
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());

        Page<BookInfo> pageResult = bookInfoRepository.search(searchQuery);
        return pageResult.getContent();
    }
}
