package com.wjh.xss_back.web;

import com.wjh.xss_back.beans.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangjunhong
 * @description LoginControllerTest
 * @since 2023/3/28 14:28
 */
@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;


    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("adm2in");
        System.out.println(userController.login(user));
    }
}
