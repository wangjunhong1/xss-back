package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjh.xss_back.beans.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjunhong
 * @description UserMapper
 * @since 2023/3/28 13:54
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
