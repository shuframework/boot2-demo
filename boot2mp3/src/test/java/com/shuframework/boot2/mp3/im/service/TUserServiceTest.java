package com.shuframework.boot2.mp3.im.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuframework.boot2.mp3.im.model.TUser;
import com.shuframework.boot2.mp3.im.query.TUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserServiceTest {

    @Autowired
    TUserService tuserService;


    @Test
    public void getById_test() {
        TUser user = tuserService.getById(1);
        System.out.println(user);
    }

    @Test
    public void query_test(){
//        List<TUser> list = tuserService.lambdaQuery().likeRight(TUser::getUsername, "9774").list();
        List<TUser> list = tuserService.query().eq(username, "9774").list();
        System.out.println(list);
    }

}