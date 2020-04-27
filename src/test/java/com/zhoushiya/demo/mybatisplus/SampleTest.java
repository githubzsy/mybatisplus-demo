package com.zhoushiya.demo.mybatisplus;

import com.zhoushiya.demo.dao.UserMapper;
import com.zhoushiya.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> users= userMapper.selectList(null);
        System.out.println(users);
    }
}
