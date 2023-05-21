package com.wjh.xss_back.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjh.xss_back.beans.*;
import com.wjh.xss_back.response.KeywordCount;
import com.wjh.xss_back.service.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author wangjunhong
 * @description StatisticsController
 * @since 2023/4/23 16:19
 */
@RestController
@Slf4j
@RequestMapping("/statistics")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class StatisticsController {
    @Autowired
    FileDetectRecorderService fileDetectRecorderService;
    @Autowired
    TextService textService;

    @Autowired
    DetectResultService detectResultService;

    @Autowired
    DetectRecorderService detectRecorderService;

    @Autowired
    FullDetectResultService fullDetectResultService;

    @Autowired
    TextDetectRecorderService textDetectRecorderService;

    @GetMapping("/card_data")
    public String[] getCardData(String username) {
        String[] cardData = new String[4];
        QueryWrapper<FileDetectRecorder> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        cardData[0] = String.valueOf(fileDetectRecorderService.list(wrapper).size());
        QueryWrapper<TextDetectRecorder> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("username", username);
        cardData[1] = String.valueOf(textDetectRecorderService.list(wrapper2).size());
        QueryWrapper<FullDetectResult> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("result", "攻击样本");
        wrapper3.eq("username", username);
        cardData[2] = String.valueOf(fullDetectResultService.list(wrapper3).size());
        wrapper3.clear();
        wrapper3.eq("result", "正常样本");
        wrapper3.eq("username", username);
        cardData[3] = String.valueOf(fullDetectResultService.list(wrapper3).size());
        log.error("return : " + cardData);
        return cardData;
    }

    @GetMapping("/bar_data")
    public List<Long>[] getBarData(String username) {
        List<String> thisWeek = getThisWeek();
        List<Long> xssCountList = new ArrayList<>();
        List<Long> normalCountList = new ArrayList<>();
        for (String date : thisWeek) {
            QueryWrapper<DetectRecorder> queryWrapper = new QueryWrapper<>();
            queryWrapper.le("start_date", date + "23:59:59");
            queryWrapper.ge("finish_date", date + "00:00:00");
            queryWrapper.eq("username", username);
            List<DetectRecorder> detectRecorderList = detectRecorderService.list(queryWrapper);
            List<String> detectRecorderIds = detectRecorderList
                    .stream().map(detectRecorder -> detectRecorder.getId()).collect(Collectors.toList());
            System.out.println(detectRecorderIds);
            if (detectRecorderIds.size() <= 0) {
                normalCountList.add(0l);
                xssCountList.add(0l);
                continue;
            }
            QueryWrapper<DetectResult> resultQueryWrapper = new QueryWrapper<>();
            resultQueryWrapper.in("detect_recorder_id", detectRecorderIds);
            List<DetectResult> detectResults = detectResultService.list(resultQueryWrapper);
            long normal_count = detectResults.stream()
                    .filter(detectResult -> detectResult.getResult().equals("正常样本")).count();
            normalCountList.add(normal_count);
            long xss_count = detectResults.stream()
                    .filter(detectResult -> detectResult.getResult().equals("攻击样本")).count();
            xssCountList.add(xss_count);
        }
        return new List[]{xssCountList, normalCountList};
    }


    @GetMapping("/pie_data")
    public List<KeywordCount> getPieData(String username) {
        List<KeywordCount> keywordCountList = new ArrayList<>();
        QueryWrapper<FullDetectResult> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<String> keywords = fullDetectResultService.list(wrapper).stream().map(detectResult -> detectResult.getKeyword()).collect(Collectors.toList());
        Map<String, Integer> keyword_ = new HashMap<>();
        for (String keyword : keywords) {
            String[] split = keyword.replaceAll("[\\[\\]',]", "").split("\\s+");
            for (String s : split) {
                if (Strings.isEmpty(s) || Strings.isBlank(s)) continue;
                if (keyword_.containsKey(s)) {
                    keyword_.replace(s, keyword_.get(s) + 1);
                } else {
                    keyword_.put(s, 1);
                }
            }
        }
        keyword_.forEach((key, value) -> {
            keywordCountList.add(new KeywordCount(key, value));
        });
        return keywordCountList;
    }

    private List<String> getThisWeek() {
        Date date = new Date();
        int weekday = date.getDay();
        List<Date> thisWeek = new ArrayList<>();
        for (int i = 1; i <= weekday; i++) {
            Date d = new Date();
            d.setDate(date.getDate() - i);
            thisWeek.add(d);
        }
        thisWeek.add(date);
        for (int i = 1; i <= 6 - weekday; i++) {
            Date d = new Date();
            d.setDate(date.getDate() + i);
            thisWeek.add(d);
        }
        thisWeek.sort((o1, o2) -> {
            if (o1.getTime() < o2.getTime()) {
                return -1;
            } else if (o1.getTime() > o2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        });
        List<String> dates = thisWeek.stream()
                .map(d -> new SimpleDateFormat("yyyy-MM-dd ").format(d))
                .collect(Collectors.toList());
        return dates;
    }
}
