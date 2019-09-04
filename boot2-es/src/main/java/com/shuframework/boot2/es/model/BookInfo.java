package com.shuframework.boot2.es.model;

import com.shuframework.commonbase.util.lang.DateUtil;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 普通的pojo
 * 
 * @author shuheng
 *
 */
//使用SpringData的时候，它需要在实体类中设置indexName 和type ，如果和传统型数据库比较的话，就相当于库和表。需要注意的是indexName和type都必须是小写!!!
@Data
@Document(indexName = "testes", type = "bookinfo")
public class BookInfo {

	private Integer id;

	private String name;

	private Double price;

	private BigDecimal priceBig;

	private Date createTime;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
