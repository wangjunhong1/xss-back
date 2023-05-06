package com.wjh.xss_back.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author wangjunhong
 * @description StatisticsControllerTest
 * @since 2023/4/23 18:51
 */
@SpringBootTest
@Slf4j
public class StatisticsControllerTest {
    @Autowired
    StatisticsController controller;

    @Test
    public void testGetBarData(){
        for (List<Long> barDatum : controller.getBarData()) {
            System.out.println(barDatum);
        }
    }
}
