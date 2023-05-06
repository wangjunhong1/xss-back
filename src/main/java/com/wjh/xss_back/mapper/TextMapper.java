package com.wjh.xss_back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjh.xss_back.beans.Text;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjunhong
 * @description TextMapper
 * @since 2023/4/13 15:46
 */
@Mapper
public interface TextMapper extends BaseMapper<Text> {
}
