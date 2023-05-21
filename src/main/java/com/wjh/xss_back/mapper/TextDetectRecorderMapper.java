package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjh.xss_back.beans.TextDetectRecorder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjunhong
 * @description TextDetectRecorderMapper
 * @since 2023/5/22 00:41
 */
@Mapper
public interface TextDetectRecorderMapper extends BaseMapper<TextDetectRecorder> {
}
