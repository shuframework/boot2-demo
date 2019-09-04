package com.shuframework.boot2.es.controller;

import com.shuframework.boot2.es.model.BookInfo;
import com.shuframework.boot2.es.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shuheng
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookInfoService bookInfoService;

    @PostMapping("/save")
    public boolean save(@RequestBody BookInfo bookInfo) {
        return bookInfoService.save(bookInfo);
    }

    @GetMapping("/searchContent")
    public List<BookInfo> search(@RequestParam(value = "searchContent") String searchContent) {
        return bookInfoService.search(searchContent);
    }

    @GetMapping("/search")
    public List<BookInfo> search4Page(@RequestParam(value = "pageIndex") Integer pageIndex,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return bookInfoService.search4Page(pageIndex, pageSize, searchContent);
    }

    @GetMapping("/searchByWeight")
    public List<BookInfo> searchByWeight(@RequestParam(value = "searchContent") String searchContent) {
        return bookInfoService.searchByWeight(searchContent);
    }

}
