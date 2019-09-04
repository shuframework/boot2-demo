package com.shuframework.boot2.esclient.config;

/**
 * @author shuheng
 */
public class EsRestClient {

    // 同步新增
    public boolean insert() {
        return true;
    }

    // 异步新增
    public boolean insertAsync() {
        return true;
    }

    // 同步修改
    public boolean update() {
        return true;
    }

    // 异步修改
    public boolean updateAsync() {
        return true;
    }

    // 同步删除
    public boolean delete() {
        return true;
    }

    // 异步删除
    public boolean deleteAsync() {
        return true;
    }

    // 主键获取记录
    public <T> T get(Class<T> clazz) {
        return null;
    }


}
