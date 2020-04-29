package com.zhoushiya.demo.mybatisplus.book;

import com.zhoushiya.demo.generator.biz.entity.Book;
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
        Book book = new Book();
        book.setName("AR插入书籍");
        book.setCreateTime(LocalDateTime.now());

        boolean r = book.insert();
        Assert.assertEquals(true, r);
    }

    @Test
    public void selectById() {
        Book book = new Book();
        Book tempBook = book.selectById("1255338461419192321");
        Assert.assertEquals("AR插入用户", tempBook.getName());
    }

//    @Test
//    public void updateById() {
//        Book book = new Book();
//        book.setId("1255338461419192321");
//        book.setName("AR");
//        boolean r = book.updateById();
//        Assert.assertEquals(true, r);
//    }
//
//    @Test
//    public void deleteById() {
//        Book book = new Book();
//        book.setId("1255338461419192321");
//        boolean r = book.deleteById();
//        Assert.assertEquals(true, r);
//    }
//
//    /**
//     * insertOrUpdate，当设定了id后则会进行Update，没设置则是insert
//     */
//    @Test
//    public void insertOrUpdate(){
//        Book book = new Book();
//        book.setId("1255338461419192321");
//        book.setName("insertOrUpdate");
//        boolean r = book.insertOrUpdate();
//        Assert.assertEquals(true, r);
//    }
}
