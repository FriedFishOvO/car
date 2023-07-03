package com.example;

import cn.dev33.satoken.stp.StpUtil;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarSpringBootApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//        Long id = userService.getIdByUsernameAndPassword("admin", "123456");
//        System.out.println(id);
        StpUtil.logout();
    }

}
