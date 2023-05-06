package com.wjh.xss_back.beans;

import lombok.Data;

/**
 * @author wangjunhong
 * @description DetectResult
 * @since 2023/4/13 15:38
 */
@Data
public class DetectResult {
    private String id;
    private String detectRecorderId;
    private String result;
    private String keyword;
}
