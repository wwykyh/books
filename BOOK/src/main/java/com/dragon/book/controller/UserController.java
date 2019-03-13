package com.dragon.book.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragon.book.model.TSysUser;
import com.dragon.book.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录处理
     *
     * @param username 用户名
     * @param pwd      密码
     * @param session
     * @return
     */
    @PostMapping("/dologin")
    public String doLogin(@RequestParam String username,
                          @RequestParam String pwd, HttpSession session,
                          HttpServletRequest request) {
        String pass = userService.encryption(pwd);
        TSysUser user = userService.getUser(username, pass);

        if (user != null) {
            session.setAttribute("user", user);
        //    session.setAttribute("userName", user.getXm());
            session.setMaxInactiveInterval(30 * 60);
            if (user.getXm().equals("admin")) {
                return "adminIndex";
            }
            return "index";
        } else {
            request.setAttribute("tip", "用户名或者密码错误，请重新输入");
            return "login";
        }

    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userName");
        return "login";
    }

    /**
     * 注册界面
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册界面
     *
     * @return
     */
    @GetMapping("/addUser")
    public String addUser() {
        return "usermanage/addUser";
    }
    /**
     * 处理注册
     *
     * @param username 用户名
     * @param pwd      密码
     * @param email    邮箱
     * @return
     */
    @PostMapping("/doreg")
    public String doRegister(@RequestParam String username,
                             @RequestParam String pwd, @RequestParam String email,
                             HttpServletRequest request) throws Exception {
        int i = 0;
        try {
            String pass = userService.encryption(pwd);
            i = userService.regUser(username, pass, email);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        if (i == 0) {
            request.setAttribute("tip", "注册失败");

            return "register";
        } else {
            request.setAttribute("tip", "注册成功");
            return "login";
        }

    }

    /**
     * ajax注册时检查用户名是否被注册
     *
     * @param username 用户名
     * @return 返回0 被注册，返回1未被注册
     */
    @GetMapping("/checkName")
    @ResponseBody
    public String selectByName(@RequestParam("username") String username) {
        TSysUser user = userService.checkUsername(username);
        if (user != null) {
            return "0";
        } else {
            return "1";
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
