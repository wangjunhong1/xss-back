package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.File;
import com.wjh.xss_back.mapper.FileMapper;
import org.springframework.stereotype.Service;

/**
 * @author wangjunhong
 * @description FileService
 * @since 2023/4/6 01:15
 */
@Service
public class FileService extends ServiceImpl<FileMapper, File> {
}
