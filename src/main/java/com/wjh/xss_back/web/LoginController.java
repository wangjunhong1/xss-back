package com.wjh.xss_back.web;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjh.xss_back.beans.User;
import com.wjh.xss_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author wangjunhong
 * @description LoginController
 * @since 2023/3/28 14:12
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        HashMap map = new HashMap();
        User loginUser = null;
        try {
            loginUser = userService.getOne(queryWrapper);
            map.put("loginUser", loginUser);
            if (loginUser != null) {
                map.put("state", 200);
                map.put("message", "登陆成功");
            } else {
                map.put("state", 300);
                map.put("message", "登录失败，检查用户名和密码");
            }
        } catch (Exception e) {
            map.put("state", 500);
            map.put("message", "服务器异常，请稍后再试");
        } finally {
            return JSON.toJSONString(map);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }
}
