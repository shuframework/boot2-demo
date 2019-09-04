package com.shuframework.boot2.es.service;

import com.shuframework.boot2.es.model.BookInfo;

import java.util.List;

/**
 * @author shuheng
 */
public interface BookInfoService {

    //相当于 insert和update
    boolean save(BookInfo bookInfo);

    /**
     * 根据关键字进行全文搜索
     * @param searchContent
     * @return
     */
    List<BookInfo> search(String searchContent);

    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<BookInfo> searchByWeight(String searchContent);

    /**
     * 根据关键字进行全文搜索 并分页
     * @param pageIndex
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<BookInfo> search4Page(Integer pageIndex, Integer pageSize, String searchContent);

}
