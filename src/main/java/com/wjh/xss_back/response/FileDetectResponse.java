package com.wjh.xss_back.response;

import lombok.Data;

/**
 * @author wangjunhong
 * @description FileDetectResponse
 * @since 2023/4/14 20:12
 */
@Data
public class FileDetectResponse {
    private Integer index;
    private String url;
    private String keyword;
    private String model;
    private String date;
    private String label;
}
