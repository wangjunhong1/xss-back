package com.wjh.xss_back.web;

import com.wjh.xss_back.beans.DetectRecorder;
import com.wjh.xss_back.beans.FileDetectRecorder;
import com.wjh.xss_back.beans.FullDetectResult;
import com.wjh.xss_back.service.DetectRecorderService;
import com.wjh.xss_back.service.FileDetectRecorderService;
import com.wjh.xss_back.service.FullDetectResultService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author wangjunhong
 * @description FullDetectResultController
 * @since 2023/4/23 21:37
 */
@SpringBootTest
@Slf4j
public class FullDetectResultController {
    @Autowired
    FullDetectResultService fullDetectResultService;

    @Autowired
    FileDetectRecorderService fileDetectRecorderService;

    @Autowired
    DetectRecorderService detectRecorderService;

    @Test
    public void testList() {
        List<FullDetectResult> list = fullDetectResultService.list();
        for (FullDetectResult fullDetectResult : list) {
            log.error(fullDetectResult.toString());
        }
    }

    @Test
    public void testList2() {
        List<FileDetectRecorder> list = fileDetectRecorderService.list();
        for (FileDetectRecorder fileDetectRecorder : list) {
            log.error(fileDetectRecorder.toString());
        }
    }

    @Test
    public void testList3() {
        List<DetectRecorder> list = detectRecorderService.list();
        for (DetectRecorder detectRecorder : list) {
            log.error(detectRecorder.toString());
        }
    }
}
