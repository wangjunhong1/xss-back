package com.wjh.xss_back.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeywordCount {
    private String name;
    private Integer value;
}