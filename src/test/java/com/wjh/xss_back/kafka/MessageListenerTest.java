package com.wjh.xss_back.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangjunhong
 * @description MessageListenerTest
 * @since 2023/3/31 01:18
 */
@SpringBootTest
public class MessageListenerTest {
    @Autowired
    MessageListener messageListener;

    @Test
    public void testConsumer() {

    }
}
