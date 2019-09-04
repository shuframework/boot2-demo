package com.shuframework.boot2.mp3.test;

import com.shuframework.boot2.mystart.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 测试
 * @author shuheng
 */
@RestController
public class HelloController {

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//	@GetMapping("/hello")
//    public String hello() {
//	    String msg = "hello spring boot";
//        redisTemplate.opsForValue().set("test:hello", msg, 30, TimeUnit.SECONDS);
//	    return msg;
//    }

    @Autowired
    private SayHelloService sayHelloService;


    //修改配置文件
	@GetMapping("/sayHello")
    public String sayHello() {
        String msg = sayHelloService.sayHello();
        return msg;
    }
}
