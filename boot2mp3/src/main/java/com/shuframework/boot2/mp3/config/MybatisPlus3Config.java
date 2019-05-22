package com.shuframework.boot2.mp3.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp3的配置
 * @author shuheng
 */
@Configuration
@MapperScan("com.shuframework.boot2.mp3.**.mapper")
// 扫描dao层接口
public class MybatisPlus3Config {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
//        page.setDialectType("mysql");
        page.setDialectType(DbType.MYSQL.getDb());
        return page;
    }
}
