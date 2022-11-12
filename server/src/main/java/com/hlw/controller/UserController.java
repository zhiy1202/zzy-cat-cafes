package com.hlw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hlw.entity.User;
import com.hlw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hlw
 * @since 2022-09-09
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    //返回用户信息
    @GetMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        return userMapper.selectById(userId);
    }
    @PutMapping("/updateUserById")
    @ResponseBody
    public Boolean updateUserById(User user,HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        user.setUserId(userId);
        int i = userMapper.updateById(user);
        return i != -1 ? true : false;
    }
    //用户数量
    @GetMapping("/userCount")
    @ResponseBody
    public Integer userCount(){
        return userMapper.selectCount(null);
    }

    //所有用户
    @GetMapping("/allUser")
    @ResponseBody
    public List<User> allUser(){
        return userMapper.selectList(null);
    }
    //通过id删除
    @DeleteMapping("/delUser/{userId}")
    @ResponseBody
    public Boolean delUser(@PathVariable("userId") Integer userId){
        int i = userMapper.deleteById(userId);
        return i != -1 ? true : false;
    }
    //用户注册
    @PostMapping("/addUser")
    public String addUser(User user, Model model){
        if (userIsExits(user.getUsername())){
            user.setIsAdmin(false);
            userMapper.insert(user);
            model.addAttribute("register","注册成功请登录");
        }else {
            model.addAttribute("register","用户名已存在请换一个");
        }

        return "user/userRegister";
    }
    public boolean userIsExits(String username){
        User username1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (username1 == null){
            return true;
        }else {
            return false;
        }
    }
}
