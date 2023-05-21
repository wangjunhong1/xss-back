package com.wjh.xss_back.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author wangjunhong
 * @description FullDetectResult
 * @since 2023/4/23 20:29
 */
@Data
public class FullDetectResult {
    private String url;
    private String useModel;
    private String startDate;
    private String finishDate;
    private String result;
    private String keyword;
    private String username;
    private String fileId;
}
