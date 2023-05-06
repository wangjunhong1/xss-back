package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.User;
import com.wjh.xss_back.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author wangjunhong
 * @description UserService
 * @since 2023/3/28 14:06
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
