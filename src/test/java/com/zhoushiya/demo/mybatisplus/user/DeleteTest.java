package com.zhoushiya.demo.mybatisplus.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhoushiya.demo.generator.biz.mapper.UserMapper;
import com.zhoushiya.demo.generator.biz.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void DeleteById() {
        int rows = userMapper.deleteById("1254654283014975490");
        Assert.assertEquals(1, rows);
    }

    /**
     * 通过map删除
     */
    @Test
    public void DeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王五");

        int rows = userMapper.deleteByMap(map);
        Assert.assertEquals(1, rows);
    }

    /**
     * 删除多个id
     */
    @Test
    public void DeleteBatchIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList("1254653624966434818"," 1254652491254452226"));
        Assert.assertEquals(2, rows);
    }

    /**
     * 通过lambda表达式删除
     */
    @Test
    public void DeleteByWrapper(){
       LambdaQueryWrapper<User> lambdaQueryWrapper= Wrappers.lambdaQuery();
       lambdaQueryWrapper.eq(User::getName,"lisi");
        int rows = userMapper.delete(lambdaQueryWrapper);
        Assert.assertEquals(1, rows);
    }
}
