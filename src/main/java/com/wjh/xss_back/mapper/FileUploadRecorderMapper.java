package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjh.xss_back.beans.FileUploadRecorder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjunhong
 * @description FileUpload
 * @since 2023/4/6 02:19
 */
@Mapper
public interface FileUploadRecorderMapper extends BaseMapper<FileUploadRecorder> {

}
