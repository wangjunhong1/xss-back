package com.wjh.xss_back.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class DetectResult {
    private String date;
    private String use_time;
    private List<Res> model_res;

    @Data
    @AllArgsConstructor
    public static class Res {
        String model_name;
        String result;
    }
}