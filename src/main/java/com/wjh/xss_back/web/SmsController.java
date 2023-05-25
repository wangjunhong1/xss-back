package com.wjh.xss_back.web;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.models.PhoneNumberInfo;
import com.wjh.xss_back.uitl.CodeSender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunhong
 * @description SmsController
 * @since 2023/5/25 22:15
 */
@RestController
@RequestMapping("/sms")
@Slf4j
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class SmsController {
    @PostMapping("/send")
    public Map send(@RequestBody PhoneNumberInfo phoneNumber) {
        log.error(phoneNumber.getPhoneNumber());
        Map res = new HashMap();
        try {
            String sms_code = CodeSender.sendCode(phoneNumber.getPhoneNumber());
            res.put("sms_code", sms_code);
            res.put("status", "200");
        } catch (TencentCloudSDKException e) {
            res.put("status", "301");
            throw new RuntimeException(e);
        }
        return res;
    }

    @Data
    public static class PhoneNumberInfo {
        private String phoneNumber;
    }
}
