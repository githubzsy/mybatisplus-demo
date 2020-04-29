package com.zhoushiya.demo.mybatisplus;

import com.zhoushiya.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {
    @Test
    public void insert() {
        User user = new User();
        user.setName("AR插入用户");
        user.setCreateTime(LocalDateTime.now());

        boolean r = user.insert();
        Assert.assertEquals(true, r);
    }

    @Test
    public void selectById() {
        User user = new User();
        User tempUser = user.selectById("1255338461419192321");
        Assert.assertEquals("AR插入用户", tempUser.getName());
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId("1255338461419192321");
        user.setName("AR");
        boolean r = user.updateById();
        Assert.assertEquals(true, r);
    }

    @Test
    public void deleteById() {
        User user = new User();
        user.setId("1255338461419192321");
        boolean r = user.deleteById();
        Assert.assertEquals(true, r);
    }

    /**
     * insertOrUpdate，当设定了id后则会进行Update，没设置则是insert
     */
    @Test
    public void insertOrUpdate(){
        User user = new User();
        user.setId("1255338461419192321");
        user.setName("insertOrUpdate");
        boolean r = user.insertOrUpdate();
        Assert.assertEquals(true, r);
    }
}
