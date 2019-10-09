package com.testspringboot.demo.User.Controller;

import com.testspringboot.demo.User.Entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Api(description = "公用接口")
@RestController
@RequestMapping("pub")
public class PublicController {

    @ApiOperation(value = "登录",notes = "登录接口")
    @PostMapping("login")
    public Object login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> info = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            subject.login(usernamePasswordToken);
            info.put("msg", "success");
            info.put("sessionId", subject.getSession().getId());
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            info.put("msg", "login filed");
            return info;
        }


    }


}
