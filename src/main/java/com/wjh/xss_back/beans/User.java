package com.wjh.xss_back.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangjunhong
 * @description User
 * @since 2023/3/28 14:01
 */
@Data
@TableName("user")
public class User {
    private String username;
    private String password;
}
