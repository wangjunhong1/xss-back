package com.wjh.xss_back.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wangjunhong
 * @description DetectRecorder
 * @since 2023/4/13 15:37
 */
@Data
public class DetectRecorder {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String textId;
    @TableField("use_model")
    private String model;
    private Date startDate;
    private Date finishDate;
    private String username;
}
