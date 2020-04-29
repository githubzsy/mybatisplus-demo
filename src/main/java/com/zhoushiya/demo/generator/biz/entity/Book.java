package com.zhoushiya.demo.generator.biz.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 书
 * </p>
 *
 * @author zhoushiya
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String name;

    private LocalDateTime createTime;


}
