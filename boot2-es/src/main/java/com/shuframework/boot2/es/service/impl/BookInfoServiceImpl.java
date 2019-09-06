package com.shuframework.boot2.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.shuframework.boot2.es.enums.IndexEnum;
import com.shuframework.boot2.es.model.BookInfo;
import com.shuframework.boot2.es.service.BookInfoService;
import com.shuframework.boot2.esclient.config.EsRestClient;
import com.shuframework.commonbase.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author shuheng
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private EsRestClient esRestClient;

    private final String BOOK_INDEX = IndexEnum.BOOK1.getIndex();

    @Override
    public boolean save(BookInfo bookInfo) {
        bookInfo.setCode(SystemUtil.getOrderCode());
        bookInfo.setCreateTime(new Date());
//        bookInfo.setCreate_time(new Date());
        return esRestClient.insert(BOOK_INDEX, bookInfo.getCode(), JSON.toJSONString(bookInfo));
    }

    @Override
    public BookInfo get(String code) {
        return esRestClient.get(BOOK_INDEX, code, BookInfo.class);
    }

    //    @Override
//    public List<BookInfo> search(String searchContent) {
//        QueryStringQueryBuilder build = new QueryStringQueryBuilder(searchContent);
//        System.out.println("查询语句："+build.toString());
//        return searchByQuery(build);
//    }
//
//    @Override
//    public List<BookInfo> searchByWeight(String searchContent) {
//        FunctionScoreQueryBuilder build = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", searchContent));
//        System.out.println("查询语句："+build.toString());
//        return searchByQuery(build);
//    }
//
//    private List<BookInfo> searchByQuery(QueryBuilder build) {
//        Iterable<BookInfo> searchResult = esRestClient.search(build);
////        // 等价于下面的方法
////        Iterator<BookInfo> iterator = searchResult.iterator();
////        List<BookInfo> list = new ArrayList<>();
////        while (iterator.hasNext()) {
////            list.add(iterator.next());
////        }
//        List<BookInfo> list = Lists.newArrayList(searchResult);
//        return list;
//    }
//
//    @Override
//    public List<BookInfo> search4Page(Integer pageIndex, Integer pageSize, String searchContent) {
//        //查询条件
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
//        //分页对象
//        PageRequest pageable = PageRequest.of(pageIndex, pageSize);
//        //组装的最后条件
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
//        System.out.println("查询的searchQuery:" + searchQuery.toString());
//        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
//
//        Page<BookInfo> pageResult = esRestClient.search(searchQuery);
//        return pageResult.getContent();
//    }
}
