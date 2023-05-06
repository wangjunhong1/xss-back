package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjh.xss_back.beans.FileDetectRecorder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjunhong
 * @description FileDetectRecorderMapper
 * @since 2023/4/13 16:11
 */
@Mapper
public interface FileDetectRecorderMapper extends BaseMapper<FileDetectRecorder> {
}
