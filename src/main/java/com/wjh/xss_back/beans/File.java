package com.wjh.xss_back.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author wangjunhong
 * @description File
 * @since 2023/4/6 01:20
 */
@Data
public class File {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String fileName;
    private float fileSize;
    private Integer recorderNum;
}
