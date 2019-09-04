package com.shuframework.boot2.mystart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装载配置类
 * @author shuheng
 */
@Configuration
@EnableConfigurationProperties({HelloProperties.class})
@ConditionalOnClass({SayHelloService.class}) // 如果存在 则整合到系统中
@ConditionalOnProperty(prefix = "spring.hello", value = "enabled", matchIfMissing = true)
public class SayHelloAutoConfigration {

    @Autowired
    private HelloProperties helloProperties;

    @Bean
    @ConditionalOnMissingBean({SayHelloService.class}) //bean实例是否存在，不存在则进行创建
    public SayHelloService sayHelloService() {
        SayHelloService sayHelloService = new SayHelloService();
        //给属性赋值
        sayHelloService.setMsg(helloProperties.getMsg());
        return sayHelloService;
    }

}
