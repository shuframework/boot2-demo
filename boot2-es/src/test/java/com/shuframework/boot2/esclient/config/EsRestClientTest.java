package com.shuframework.boot2.esclient.config;

import com.alibaba.fastjson.JSON;
import com.shuframework.boot2.es.enums.IndexEnum;
import com.shuframework.boot2.es.model.BookInfo;
import com.shuframework.commonbase.util.SystemUtil;
import com.shuframework.commonbase.util.lang.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsRestClientTest {

    @Autowired
    private EsRestClient esRestClient;

    private final String BOOK1_INDEX = IndexEnum.BOOK1.getIndex();

    private final String SHQ_GAME_CONFIGS = IndexEnum.SHQ_GAME_CONFIGS.getIndex();
    private final String SHQ_GAME_MODULES = IndexEnum.SHQ_GAME_MODULES.getIndex();



    @Test
    public void shq_game_modules() {
        Map<String, Object> propertiesMap = initBasePorp();

        Map<String, Object> uploadtime = new HashMap<>();
        uploadtime.put("type", "text");
        Map<String, Object> game = new HashMap<>();
        game.put("type", "keyword");

        Map<String, Object> path = new HashMap<>();
        path.put("type", "text");
        Map<String, Object> filesize = new HashMap<>();
        filesize.put("type", "long");
        Map<String, Object> validate_level = new HashMap<>();
        validate_level.put("type", "short");

        propertiesMap.put("uploadtime", uploadtime);
        propertiesMap.put("game", game);
        propertiesMap.put("path", path);
        propertiesMap.put("filesize", filesize);
        propertiesMap.put("validate_level", validate_level);

        createIndex(propertiesMap, SHQ_GAME_MODULES);
    }

    @Test
    public void shq_game_configs() {
        Map<String, Object> propertiesMap = initBasePorp();

        Map<String, Object> uploadtime = new HashMap<>();
        uploadtime.put("type", "text");
        Map<String, Object> game = new HashMap<>();
        game.put("type", "keyword");

        Map<String, Object> kernel_detection = new HashMap<>();
        kernel_detection.put("type", "boolean");
        Map<String, Object> x64_only = new HashMap<>();
        x64_only.put("type", "boolean");
        Map<String, Object> check_safetray = new HashMap<>();
        check_safetray.put("type", "boolean");
        Map<String, Object> bypass_pg = new HashMap<>();
        bypass_pg.put("type", "boolean");
        Map<String, Object> load_driver = new HashMap<>();
        load_driver.put("type", "boolean");
        Map<String, Object> fake_password = new HashMap<>();
        fake_password.put("type", "boolean");
        Map<String, Object> suspend_login = new HashMap<>();
        suspend_login.put("type", "boolean");
        Map<String, Object> tesv_protection = new HashMap<>();
        tesv_protection.put("type", "boolean");
        Map<String, Object> restric_mode = new HashMap<>();
        restric_mode.put("type", "boolean");
        Map<String, Object> check_vm = new HashMap<>();
        check_vm.put("type", "boolean");
        Map<String, Object> obcallback_protection = new HashMap<>();
        obcallback_protection.put("type", "boolean");
        Map<String, Object> dedicate_mode = new HashMap<>();
        dedicate_mode.put("type", "boolean");

        propertiesMap.put("uploadtime", uploadtime);
        propertiesMap.put("game", game);
        propertiesMap.put("kernel_detection", kernel_detection);
        propertiesMap.put("x64_only", x64_only);
        propertiesMap.put("check_safetray", check_safetray);
        propertiesMap.put("bypass_pg", bypass_pg);
        propertiesMap.put("load_driver", load_driver);
        propertiesMap.put("fake_password", fake_password);
        propertiesMap.put("suspend_login", suspend_login);
        propertiesMap.put("tesv_protection", tesv_protection);
        propertiesMap.put("restric_mode", restric_mode);
        propertiesMap.put("check_vm", check_vm);
        propertiesMap.put("obcallback_protection", obcallback_protection);
        propertiesMap.put("dedicate_mode", dedicate_mode);

        createIndex(propertiesMap, SHQ_GAME_CONFIGS);
    }


    private Map<String, Object> initBasePorp() {
        Map<String, Object> id = new HashMap<>();
        id.put("type", "keyword");
        Map<String, Object> createUserId = new HashMap<>();
        createUserId.put("type", "text");
        Map<String, Object> updateUserId = new HashMap<>();
        updateUserId.put("type", "text");
        Map<String, Object> createTime = new HashMap<>();
        createTime.put("type", "date");
        createTime.put("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd");
        Map<String, Object> updateTime = new HashMap<>();
        updateTime.put("type", "date");
        updateTime.put("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd");

        //属性
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("id", id);
        propertiesMap.put("createUserId", createUserId);
        propertiesMap.put("updateUserId", updateUserId);
        propertiesMap.put("createTime", createTime);
        propertiesMap.put("updateTime", updateTime);
        return propertiesMap;
    }

    private void createIndex(Map<String, Object> propertiesMap, String index) {
        Map<String, Object> prop = new HashMap<>();
        prop.put("properties", propertiesMap);

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put(index, prop);
        System.out.println(JSON.toJSONString(jsonMap));

        esRestClient.createIndex(index, jsonMap);
    }


    @Test
    public void createIndex_test() {

        Map<String, Object> id = new HashMap<>();
        id.put("type", "integer");
        Map<String, Object> code = new HashMap<>();
        code.put("type", "text");
        Map<String, Object> name = new HashMap<>();
        name.put("type", "text");
        Map<String, Object> price = new HashMap<>();
        price.put("type", "float");
        Map<String, Object> createTime = new HashMap<>();
        createTime.put("type", "date");
//        createTime.put("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd");
        createTime.put("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis");

        //属性
        Map<String, Object> propertiesMap = new HashMap<>();
        propertiesMap.put("id", id);
        propertiesMap.put("code", code);
        propertiesMap.put("name", name);
        propertiesMap.put("price", price);
//        propertiesMap.put("priceBig", priceBig);
        propertiesMap.put("createTime", createTime);

        Map<String, Object> book = new HashMap<>();
        book.put("properties", propertiesMap);

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("mybook2", book);
        System.out.println(JSON.toJSONString(jsonMap));

        boolean flag = esRestClient.createIndex(BOOK1_INDEX, jsonMap);
        System.out.println(flag);
    }


    @Test
    public void insertData_test() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(2);
        bookInfo.setName("ta");
        bookInfo.setPrice(62.5);
        bookInfo.setCode(SystemUtil.getOrderCode());
        bookInfo.setCreateTime(DateUtil.strToDateShort("2019-09-06"));

        esRestClient.insert(BOOK1_INDEX, bookInfo.getCode(), JSON.toJSONString(bookInfo));
    }


    @Test
    public void deleteIndex() {
        boolean deleteFlag = esRestClient.deleteIndex(BOOK1_INDEX);
        System.out.println(deleteFlag);
    }

}