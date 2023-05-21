package com.wjh.xss_back.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjh.xss_back.beans.FullDetectResult;
import com.wjh.xss_back.service.FullDetectResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjunhong
 * @description XssListController
 * @since 2023/4/23 20:12
 */
@RestController
@RequestMapping("/detect_list")
@Slf4j
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class XssListController {

    @Autowired
    FullDetectResultService fullDetectResultService;

    @GetMapping("/list_page")
    public Page<FullDetectResult> list_(long current, long size, String username) {
        Page<FullDetectResult> page = new Page<>(current, size);
        QueryWrapper<FullDetectResult> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Page<FullDetectResult> resultPage = fullDetectResultService.page(page, wrapper);
        return resultPage;
    }

    @GetMapping("/list")
    public List<FullDetectResult> list(long start, long end, String username) {
        log.error("开始查询所有数据");
        QueryWrapper<FullDetectResult> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        if (start != 0 && end != 0) {
            Date start_date = new Date(start);
            Date end_date = new Date(end);
            wrapper.ge("start_date", start_date);
            wrapper.le("finish_date", end_date);
        }
        List<FullDetectResult> list = fullDetectResultService.list(wrapper);
        log.error("查询所有数据结束");
        return list;
    }

    @GetMapping("/filter_list")
    public Page<FullDetectResult> list_filter(long start, long end, long current, long size, String username) {
        Date start_date = new Date(start);
        Date end_date = new Date(end);
        Page<FullDetectResult> page = new Page<>(current, size);
        QueryWrapper<FullDetectResult> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.ge("start_date", start_date);
        wrapper.le("finish_date", end_date);
        Page<FullDetectResult> resultPage = fullDetectResultService.page(page, wrapper);
        return resultPage;
    }
}
