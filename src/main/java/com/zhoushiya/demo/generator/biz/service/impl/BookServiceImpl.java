package com.zhoushiya.demo.generator.biz.service.impl;

import com.zhoushiya.demo.generator.biz.entity.Book;
import com.zhoushiya.demo.generator.biz.mapper.BookMapper;
import com.zhoushiya.demo.generator.biz.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书 服务实现类
 * </p>
 *
 * @author zhoushiya
 * @since 2020-04-29
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
