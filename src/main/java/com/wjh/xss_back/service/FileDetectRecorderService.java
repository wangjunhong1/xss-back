package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.FileDetectRecorder;
import com.wjh.xss_back.mapper.FileDetectRecorderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjunhong
 * @description FileDetectRecorderService
 * @since 2023/4/13 16:14
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class FileDetectRecorderService extends ServiceImpl<FileDetectRecorderMapper, FileDetectRecorder> {
}
