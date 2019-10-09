package com.testspringboot.demo.User.Controller;

import com.testspringboot.demo.User.Entity.Role;
import com.testspringboot.demo.User.Entity.User;
import com.testspringboot.demo.User.Service.UserService;
import com.testspringboot.demo.config.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(description = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "查询权限", notes = "查询权限接口")
    @GetMapping("findRoleListByUserName")
    public Object findRoleListByUserName(@RequestBody int userId) {
        return userService.findRoleListByUserId(userId);
    }

    @ApiOperation(value = "查询权限2", notes = "查询权限接口2")
    @GetMapping("findAllUser")
    public ResultData findAllUser(@RequestParam int userId) {
        try {
            List<Role> roleList = userService.findRoleListByUserId(userId);
            return new ResultData(500, roleList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400, "权限不足");
        }


    }


}
