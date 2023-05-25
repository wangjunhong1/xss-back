package com.wjh.xss_back.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private String TOPIC = "j2p";

    public void sendMessage(String key, String message) {
        //使用send方法发送消息，需要传入topic名称
        kafkaTemplate.send(TOPIC, key, message);
    }
}
