package com.zhoushiya.demo.mybatisplus;

import com.zhoushiya.demo.dao.UserMapper;
import com.zhoushiya.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void Insert() {
        User user = new User();
        user.setName("适1时");
        user.setAge(26);
        user.setManagerId(1088248166370832385l);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数:"+rows);
        System.out.println("主键:"+user.getId());
    }
}
