package com.wjh.xss_back.kafka;

import com.alibaba.fastjson2.JSON;
import com.wjh.xss_back.p2j.Result;
import com.wjh.xss_back.web.FileDetectController;
import com.wjh.xss_back.web.TextDetectController;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @Autowired
    FileDetectController fileDetectController;

    @Autowired
    TextDetectController textDetectController;

    @KafkaListener(topics = "p2j-file")
    public void onFileMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        fileDetectController.list = JSON.parseArray(message, Result.class);
        fileDetectController.notify_();
    }

    @KafkaListener(topics = "p2j-text")
    public void onTextMessage(ConsumerRecord<String, String> record) {
        String message = record.value();
        textDetectController.list = JSON.parseArray(message, Result.class);
        textDetectController.notify_();
    }
}
