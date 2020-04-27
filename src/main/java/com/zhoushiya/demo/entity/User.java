package com.zhoushiya.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mp_user")
public class User {

    @TableId
    private Long userId;

    @TableField("name")
    private String realName;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    /**
     * 备注，数据库没有对应字段
     */
    @TableField(exist = false)
    private String remark;
}
