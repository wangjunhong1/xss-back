package com.wjh.xss_back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wjh.xss_back.mapper")
public class XssBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssBackApplication.class, args);
    }

}
