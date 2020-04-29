package com.zhoushiya.demo.mybatisplus.user;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhoushiya.demo.generator.biz.mapper.UserMapper;
import com.zhoushiya.demo.generator.biz.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void UpdateById() {
        User user = new User();
        user.setId("1088248166370832385");
        user.setAge(27);
        // 设置为null的字段不会更新
        user.setEmail(null);
        int rows = userMapper.updateById(user);
        Assert.assertEquals(1l, rows);
    }

    @Test
    public void UpdateByWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 28);
        User user = new User();
        user.setAge(29);
        int rows = userMapper.update(user, updateWrapper);
        Assert.assertEquals(1l, rows);
    }

    @Test
    public void UpdateByWrapper2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").set("age", 30);
        int rows = userMapper.update(null, updateWrapper);
        Assert.assertEquals(1l, rows);
    }

    @Test
    public void UpdateByLambda() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "李艺伟").set(User::getAge, 32);
        int rows = userMapper.update(null, lambdaUpdate);
        Assert.assertEquals(1l, rows);
    }
}
