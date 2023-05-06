package com.wjh.xss_back.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author wangjunhong
 * @description FileUploadRecorder
 * @since 2023/4/6 02:17
 */
@Data
public class FileUploadRecorder {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String fileId;
    private String srcFileName;
    private Date uploadDate;
}
