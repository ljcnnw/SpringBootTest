package com.testspringboot.demo.User.Controller;

import com.alibaba.druid.util.StringUtils;
import com.testspringboot.demo.User.Entity.User;
import com.testspringboot.demo.User.Entity.UserInfo;
import com.testspringboot.demo.User.Service.RequestTokenService;
import com.testspringboot.demo.User.Service.UserService;
import com.testspringboot.demo.comment.ApiIdempotent;
import com.testspringboot.demo.config.ResultData;
import com.testspringboot.demo.exception.IdempotentException;
import com.testspringboot.demo.util.FtpUtil;
import com.testspringboot.demo.util.RedisUtil;
import com.testspringboot.demo.util.SendMailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "公用接口")
@RestController
@RequestMapping("pub")
@CrossOrigin
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    FtpUtil ftpUtil;
    @Autowired
    private RequestTokenService requestTokenService;


    @ApiOperation(value = "登录", notes = "登录接口")
    @PostMapping("login")
    public Object login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> info = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            subject.login(usernamePasswordToken);
            System.out.println(subject.getPrincipal());
            info.put("msg", "success");
            info.put("sessionId", subject.getSession().getId());
            info.put("user", subject.getPrincipal());
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            info.put("msg", "login filed");
            return info;
        }
    }

    @ApiOperation(value = "注册", notes = "注册接口")
    @PostMapping("registUser")
    public ResultData registUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResultData(500, bindingResult.getFieldError().getDefaultMessage());
            }
            Object checkMail = RedisUtil.getInstance().get(RedisUtil.CheckMail + user.getUserInfo().getEmail());
            if (checkMail == null || StringUtils.isEmpty(user.getEmailCheckNum())) {
                return new ResultData(500, "验证码错误");
            } else if (user.getEmailCheckNum().equals(checkMail)) {
                userService.insertUser(user);
                RedisUtil.getInstance().del(RedisUtil.CheckMail + user.getUserInfo().getEmail());
                return new ResultData(200, "注册成功");
            } else {
                return new ResultData(500, "验证码错误");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "注册失败");
        }
    }


    /**
     * 发送邮箱认证码
     *
     * @param user
     * @return
     */
    @PostMapping("sendMail")
    @ApiOperation(value = "邮箱认证", notes = "邮箱认证接口")
    public ResultData sendMail(@RequestBody User user) {
        String checkNum = SendMailUtil.getInstance().getlinkNo();
        Object check = RedisUtil.getInstance().get(RedisUtil.CheckMail + user.getUserInfo().getEmail());
        if (check == null) {
            RedisUtil.getInstance().set(RedisUtil.CheckMail + user.getUserInfo().getEmail(), checkNum, 300);
        } else {
            return new ResultData(500, "验证码已发送，请去邮箱查收");
        }
        boolean flag = SendMailUtil.getInstance().sendCheckEmail(user.getUserInfo().getEmail(), "认证码邮件", checkNum);
        if (flag) {
            return new ResultData(200, "发送验证码成功");
        } else {
            return new ResultData(500, "发送验证码失败");
        }
    }

    @PostMapping("ftptest")
    public void ftptest(@Param("fileName") String fileName, @Param("originfilename") String originfilename) {
        boolean flag = ftpUtil.uploadFile("/data/", fileName, originfilename);
        if (flag) {
            System.out.println("失败");
        } else {
            System.out.println("成功");
        }
    }

    @GetMapping("addToken")
    public ResultData addToken() {
        try {
            String token = requestTokenService.createRequestToken();
            return new ResultData(200, token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(500, "fail");
        }
    }

    @GetMapping("testToken")
    @ApiIdempotent
    public ResultData testToken() {
        try {
            return new ResultData(200, "success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("多次请求错误");
            return new ResultData(500, "fail");
        }
    }

    @GetMapping("testHandler")
    public ResultData testHandler(){
        throw new IdempotentException("111");
    }

}
