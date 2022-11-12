package com.hlw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hlw.entity.User;
import com.hlw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zzy
 * @desc
 */
@Controller
public class AdminPageController {
    @Autowired
    private UserMapper userMapper;
    //管理员登录页面
    @GetMapping("/admin")
    public String adminLogin(){
        return "admin/adminLogin";
    }
    //index
    @GetMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }
    //管理员登录
    @PostMapping("/adminLogin")
    public String adminLogin(String username, String password , HttpServletRequest request , Model model){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password).eq("is_admin",true);
        User user = userMapper.selectOne(wrapper);
        if (user == null){
            model.addAttribute("login","无权限");
            return "admin/adminLogin";
        }else {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId",user.getUserId());
            session.setAttribute("admin","admin");
            return "redirect:/admin/index";
        }
    }
    //宠物管理
    @GetMapping("/petManager")
    public String petManager(){
        return "admin/petManager";
    }

    //远程操作
    @GetMapping("/petActive")
    public String petActive(){
        return "admin/petActive";
    }

    //用户管理
    @GetMapping("/userManager")
    public String userManager(){
        return "admin/userManager";
    }
    //新增宠物
    @GetMapping("/admin/addPet")
    public String adminAddPet(){
        return "admin/addPet";
    }
    //退出
    @GetMapping("/admin/exit")
    public String adminExit(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("userId");
        session.removeAttribute("admin");
        return "redirect:/admin";
    }
}
