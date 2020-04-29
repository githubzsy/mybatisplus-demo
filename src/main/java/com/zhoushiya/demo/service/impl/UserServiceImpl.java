package com.zhoushiya.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoushiya.demo.dao.UserMapper;
import com.zhoushiya.demo.entity.User;
import com.zhoushiya.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
