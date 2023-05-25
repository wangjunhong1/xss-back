package com.wjh.xss_back.uitl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author wangjunhong
 * @description CodeSender
 * @since 2023/5/25 21:35
 */
@Slf4j
public class CodeSender {

    private static final String secretId = "AKID0pOhkW1FCtJSKwGJ5csrZ0B5i4AzXopZ";
    private static final String secretKey = "UhdJjSv97oEOHtEI7fvue9NK1SbT4QWK";
    private static final String endpoint = "sms.tencentcloudapi.com";
    private static final String region = "ap-beijing";
    private static final String smsSdkAppId = "1400517293";
    private static final String signName = "樱桃夏至个人公众号";
    private static final String templateId = "1430027";
    private static final String validity = "2";
    private static SmsClient client;
    ;


    static {
        Credential credential = new Credential(secretId, secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(endpoint);
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        client = new SmsClient(credential, region, clientProfile);
    }

    public static String sendCode(String phoneNumber) throws TencentCloudSDKException {

        log.error(phoneNumber);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = {phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet1);

        req.setSmsSdkAppId(smsSdkAppId);
        req.setSignName(signName);
        req.setTemplateId(templateId);

        String[] templateParamSet1 = new String[]{generateCode(), validity};
        req.setTemplateParamSet(templateParamSet1);

        // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
        SendSmsResponse resp = client.SendSms(req);
        // 输出json格式的字符串回包
        log.error(SendSmsResponse.toJsonString(resp));
        if (resp.getSendStatusSet()[0].getCode().equals("Ok")) {
            return templateParamSet1[1];
        } else {
            throw new TencentCloudSDKException("消息发送失败");
        }
    }

    private static String generateCode() {
        int code = 0;
        for (int i = 0; i < 6; i++) {
            int t = (new Random().nextInt() % 10 + 10) % 10;
            code = code * 10 + t;
        }
        String sms_code = String.format("%06d", code);
        return sms_code;
    }

    public static void main(String[] args) throws TencentCloudSDKException {
        sendCode("15310108138");
    }
}
