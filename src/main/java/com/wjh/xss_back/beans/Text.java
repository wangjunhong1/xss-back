package com.wjh.xss_back.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author wangjunhong
 * @description Text
 * @since 2023/4/13 15:35
 */
@Data
public class Text {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String url;
    private String fileId;
}
