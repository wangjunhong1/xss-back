package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.deploy.security.ruleset.DefaultRule;
import com.wjh.xss_back.beans.DetectResult;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjunhong
 * @description DetectResult
 * @since 2023/4/13 16:13
 */
@Mapper
public interface DetectResultMapper extends BaseMapper<DetectResult> {
}
