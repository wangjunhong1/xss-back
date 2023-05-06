package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.DetectRecorder;
import com.wjh.xss_back.beans.DetectResult;
import com.wjh.xss_back.mapper.DetectRecorderMapper;
import com.wjh.xss_back.mapper.DetectResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjunhong
 * @description DetectRecorderService
 * @since 2023/4/13 16:15
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class DetectRecorderService extends ServiceImpl<DetectRecorderMapper, DetectRecorder> {
}
