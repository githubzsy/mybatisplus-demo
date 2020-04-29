package com.zhoushiya.demo.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhoushiya.demo.entity.User;
import com.zhoushiya.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void getOne() {
        User one = userService.getOne(Wrappers.<User>lambdaQuery().ge(User::getAge, 20),false);
        System.out.println(one);
    }

    @Test
    public void batch() {
        User user1=new User();
        user1.setName("十一");
        User user2=new User();
        user2.setId("5efbf79f32c1c3c7a157c6cc2a2b202d");
        user2.setName("十二二");

        List<User> users= Arrays.asList(user1,user2);
        userService.saveOrUpdateBatch(users);
    }

    /**
     * 链式表达
     */
    @Test
    public void chainSelect(){
        userService.lambdaQuery().gt(User::getAge,25).like(User::getName,"雨").list().forEach(System.out::println);
    }

    /**
     * 链式查询并且更新
     */
    @Test
    public void chainUpdate(){
        boolean r= userService.lambdaUpdate().gt(User::getAge,25).like(User::getName,"雨").set(User::getAge,26).update();
        System.out.println(r);
    }
}
