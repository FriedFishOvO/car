package com.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.response.LoginResponse;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginUser.getUsername()).eq(User::getPassword, loginUser.getPassword());
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            StpUtil.login(user.getLoginId());
            LoginResponse loginResponse = new LoginResponse((String) StpUtil.getTokenInfo().getLoginId(), user.getNickname(), user.getType());
            return Result.success(loginResponse);
        }
        return Result.error();
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody User logoutUser) {
        StpUtil.logout(logoutUser.getLoginId());
        return Result.success();
    }

    @PutMapping("/modify")
    public Result modifyInfo(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginId, user.getLoginId());
        User u = userService.getOne(queryWrapper);
        if (!"".equals(user.getNickname()))
            u.setNickname(user.getNickname());
        if (!"".equals(user.getPassword()))
            u.setPassword(user.getPassword());
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getLoginId, u.getLoginId());
        userService.update(u, updateWrapper);
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User u = userService.getOne(queryWrapper);
        if (u != null)
            return Result.error();
        userService.save(user);
        return Result.success();
    }
}
