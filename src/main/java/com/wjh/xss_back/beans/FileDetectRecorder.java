package com.wjh.xss_back.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author wangjunhong
 * @description FileDetectRecorder
 * @since 2023/4/13 15:39
 */
@Data
public class FileDetectRecorder {
    private String id;
    private String fileId;
    private Date startTime;
    private Date finishTime;
    private String useModel;
}
