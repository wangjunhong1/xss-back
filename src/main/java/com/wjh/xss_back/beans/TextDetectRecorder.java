package com.wjh.xss_back.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author wangjunhong
 * @description TextDetectRecorder
 * @since 2023/5/22 00:39
 */
@Data
public class TextDetectRecorder {
    private String textId;
    private String useModel;
    private Date startDate;
    private Date finishDate;
    private String username;
    private String url;
}
