package com.wjh.xss_back.uitl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;

import java.io.*;

/**
 * @author wangjunhong
 * @description OssUpload
 * @since 2023/5/27 11:28
 */
public class OssUpload {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static String accessKeyId = "LTAI5t724yntAPvBHEqk2LwT";
    private static String accessKeySecret = "ajNfoGwuIu2RMpltVcI2qNGdr3yw8s";
    // 填写Bucket名称，例如examplebucket。
    private static String bucketName = "xss-detect";
    // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
    private static String objectName = "csvs/";

    public static void upload(String filename, InputStream inputStream) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, objectName + filename, inputStream);
        ossClient.shutdown();
    }
}
