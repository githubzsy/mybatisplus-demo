package com.zhoushiya.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User extends Model<User> {

    private String id;

    // @TableField(condition = SqlCondition.LIKE)
    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;
}
