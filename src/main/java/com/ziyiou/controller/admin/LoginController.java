package com.ziyiou.controller.admin;

import com.ziyiou.po.User;
import com.ziyiou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author ziyiou
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminBlog(){
        return "forward:/admin/blog";
    }

    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }

    @PostMapping("/login")
    public String login(String username,
                        String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        // 查询用户是否存在
        User user = userService.checkUser(username, password);

        // 如果未查到用户，则重新登录
        if (user == null) {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin/login";
        }

        // 密码置空，传到前端
        user.setPassword(null);
        session.setAttribute("user", user);
        return "redirect:/admin/blog";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
