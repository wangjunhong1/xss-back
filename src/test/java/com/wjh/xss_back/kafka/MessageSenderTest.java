package com.wjh.xss_back.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangjunhong
 * @description MessageServiceKafkaTest
 * @since 2023/3/31 01:14
 */
@SpringBootTest
public class MessageSenderTest {
    @Autowired
    MessageSender messageSender;

    @Test
    public void testSend() {
        String file="assdadasd";
        messageSender.sendMessage("",file);
    }
}
