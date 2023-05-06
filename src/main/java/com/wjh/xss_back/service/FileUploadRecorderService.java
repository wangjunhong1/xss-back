package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.FileUploadRecorder;
import com.wjh.xss_back.mapper.FileUploadRecorderMapper;
import org.springframework.stereotype.Service;

/**
 * @author wangjunhong
 * @description FileUploadRecorderService
 * @since 2023/4/6 02:20
 */
@Service
public class FileUploadRecorderService
        extends ServiceImpl<FileUploadRecorderMapper, FileUploadRecorder> {
}
