package com.shuframework.boot2.mystart;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 专门读取配置属性的类
 * @author shuheng
 */
@ConfigurationProperties(prefix = "spring.hello")
@Data
public class HelloProperties {

    private String msg = "spring boot";


}
