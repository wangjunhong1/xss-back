package com.wjh.xss_back.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjh.xss_back.beans.Text;
import com.wjh.xss_back.mapper.TextMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjunhong
 * @description TextService
 * @since 2023/4/13 15:47
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class TextService extends ServiceImpl<TextMapper, Text> {
}
