package com.wjh.xss_back.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author wangjunhong
 * @description TextDetectResponse
 * @since 2023/4/15 19:12
 */
@Data
public class TextDetectResponse {
    private DetectResult detectResult;
    List<KeywordCount> keywordCounts;
}

