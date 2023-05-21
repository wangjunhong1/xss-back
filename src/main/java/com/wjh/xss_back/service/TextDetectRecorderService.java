package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.TextDetectRecorder;
import com.wjh.xss_back.mapper.TextDetectRecorderMapper;
import org.springframework.stereotype.Service;

/**
 * @author wangjunhong
 * @description TextDetectRecorderService
 * @since 2023/5/22 00:42
 */
@Service
public class TextDetectRecorderService extends ServiceImpl<TextDetectRecorderMapper, TextDetectRecorder> {
}
