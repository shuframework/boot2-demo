package com.shuframework.boot2.mystart;

/**
 * @author shuheng
 */
public class SayHelloService {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String sayHello() {
        return "hello " + msg;
    }
}
