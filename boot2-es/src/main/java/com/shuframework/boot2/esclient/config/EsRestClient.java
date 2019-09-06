package com.shuframework.boot2.esclient.config;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 规定每一个index只能有一个type。在es7中使用默认的_doc作为type, 后续版本会去掉 type
 *
 * @author shuheng
 */
@Component
public class EsRestClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String DEFAULT_TYPE = "item";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 批量插入对象
    private BulkProcessor bulkProcessor;

    @PostConstruct
    public void init() {
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {

            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                logger.info("Async afterBulk put success, ingestTookInMillis:{}", response.getIngestTookInMillis());
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                logger.error("Async afterBulk put error,reason:{}", failure.getMessage());
            }
        };
        /*
            @Deprecated bulkAsync(BulkRequest bulkRequest, ActionListener<BulkResponse> listener, Header... headers)
            es6 是过时，es7 删除了
            BulkProcessor.Builder builder = BulkProcessor.builder(restHighLevelClient::bulkAsync, listener);
         */
        BulkProcessor.Builder builder = BulkProcessor.builder((request, bulkListener) ->
                restHighLevelClient.bulkAsync(request, RequestOptions.DEFAULT, bulkListener), listener);
        builder.setBulkActions(1000);
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        builder.setConcurrentRequests(0);
        builder.setFlushInterval(TimeValue.timeValueSeconds(5L));
        builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3));
        bulkProcessor = builder.build();
    }

    public void destroy() {
        try {
            //执行关闭方法会把bulk剩余的数据都写入ES再执行关闭
            bulkProcessor.awaitClose(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Failed to close bulkProcessor", e);
        }
        logger.info("bulkProcessor closed!");
    }


    /**
     *
     * @param index
     * @param jsonMap   文档结构（表的ddl）
     * @return
     */
    public boolean createIndex(String index, Map<String, Object> jsonMap) {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.alias(new Alias(index + "alias"));
        request.mapping(jsonMap);
        try {
            CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            System.out.println(response.isAcknowledged());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 同步删除
    // "type":"illegal_argument_exception" 安装方式导致 index不能删除
    public boolean deleteIndex(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        try {
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(String index, String id, Map<String, Object> map) {
        IndexRequest request = new IndexRequest(index);
        request.id(id).source(map, XContentType.JSON);
        insert(request);
        return true;
    }

    // 同步新增
    public boolean insert(String index, String id, String jsonString) {
        IndexRequest request = new IndexRequest(index);
        request.id(id).source(jsonString, XContentType.JSON);
//        //与上面等效, 建议手动指定 即用上面的方式
//        request.source(jsonString);
        insert(request);
        return true;
    }

    // 初始化 IndexRequest配置, 以及保存
    private void insert(IndexRequest indexRequest) {
//        //设置超时：等待主分片变得可用的时间
//        indexRequest.timeout(TimeValue.timeValueSeconds(1));//1s
        //刷新策略
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if(response != null) {
//                long version = response.getVersion();
                logger.info("sync put response:{}", response.toString());
            }
        } catch (Exception e) {
            logger.error("sync put error,reason:", e);
        }
    }

    // 异步新增
    public boolean insertAsync(String index, String id, String jsonString) {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id(id).source(jsonString, XContentType.JSON);
        // 添加到批量对象里 会自动处理
        bulkProcessor.add(indexRequest);
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
    public boolean delete(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);

        try {
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 异步删除
    public boolean deleteAsync() {
        return true;
    }

    // 主键获取记录
    public String get2String(String index, String id) {
        GetRequest request = new GetRequest(index, DEFAULT_TYPE, id);
        GetResponse response = get(request);
        return response.getSourceAsString();
    }

    public <T> T get(String index, String id, Class<T> clazz) {
        String result = get2String(index, id);
        T t = JSON.parseObject(result, clazz);
        return t;
    }

    // 主键获取记录
    public Map<String, Object> get2Map(String index, String id) {
        GetRequest request = new GetRequest(index, DEFAULT_TYPE, id);
        GetResponse response = get(request);
        return response.getSourceAsMap();
    }

    private GetResponse get(GetRequest request) {
        try {
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            return response;
        } catch (Exception e) {
            logger.error("sync put error,reason:", e);
        }
        return null;
    }

    private ActionListener initActionListener() {
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                //执行成功时调用。 Response以参数方式提供
            }

            @Override
            public void onFailure(Exception e) {
                //在失败的情况下调用。 引发的异常以参数方式提供
            }
        };
        return listener;
    }


}
