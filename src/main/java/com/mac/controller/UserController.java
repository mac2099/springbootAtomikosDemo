package com.mac.controller;

import com.mac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>description：</p>
 * <p>copyright： copyright(C)2016-2099</p>
 * <p>Life is short,we need passion</p>
 * <p>Summary： </p>
 * <p>instructions： </p>
 * Date 2020-01-17</p>
 * Author mac
 *
 * @version 1.0
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/addUser")
    public String addUser() {
        return userService.addUser1AndUser2() + "条";
    }
}
