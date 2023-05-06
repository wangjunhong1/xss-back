package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjh.xss_back.beans.File;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author wangjunhong
 * @description FileMapper
 * @since 2023/4/6 01:15
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {
}
