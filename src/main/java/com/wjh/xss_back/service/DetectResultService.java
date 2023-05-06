package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.DetectResult;
import com.wjh.xss_back.mapper.DetectResultMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author wangjunhong
 * @description DetectResultService
 * @since 2023/4/13 16:16
 */
@Service
public class DetectResultService extends ServiceImpl<DetectResultMapper, DetectResult> {
}
