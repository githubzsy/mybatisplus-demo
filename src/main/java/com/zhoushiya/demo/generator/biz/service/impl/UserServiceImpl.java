package com.zhoushiya.demo.generator.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoushiya.demo.generator.biz.mapper.UserMapper;
import com.zhoushiya.demo.generator.biz.entity.User;
import com.zhoushiya.demo.generator.biz.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
