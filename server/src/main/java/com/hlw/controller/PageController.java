package com.hlw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hlw.entity.Pet;
import com.hlw.entity.User;
import com.hlw.mapper.PetMapper;
import com.hlw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@Controller
public class PageController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PetMapper petMapper;

    @GetMapping("/")
    public String userWelcome(){
        return "user/userLogin";
    }
    //用户登录
    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpServletRequest request){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(wrapper);
        if (user != null){

            request.getSession(true).setAttribute("userId",user.getUserId());
            return "redirect:/index";
        }else {
            model.addAttribute("login","账号不存在或者密码错误");
            return "user/userLogin";
        }
    }
    @GetMapping("/index")
    public String index(Model model){
        QueryWrapper<Pet> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("is_shop",1);
        List<Pet> petList = petMapper.selectList(wrapper1);
        model.addAttribute("petList",petList);


        return "user/index";
    }
    //宠物详情页面
    @GetMapping("/petDetail/{petId}")
    public String petDetail(@PathVariable("petId") Integer petId,Model model,HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        User user = userMapper.selectById(userId);
        model.addAttribute("user",user);

        Pet pet = petMapper.selectById(petId);
        model.addAttribute("pet",pet);
        return "user/petDetail";
    }
    //跳转寄养页面
    @GetMapping("/petUpload")
    public String petUpload(){
        return "user/petUpload";
    }
    //跳转出售页面
    @GetMapping("/shopPet")
    public String shopPet(){
        return "user/shopPet";
    }
    //跳转到个人信息页面
    @GetMapping("/mySelf")
    public String mySelf(){
        return "user/mySelf";
    }
    //跳转购买的宠物
    @GetMapping("/userBuyPet")
    public String userBuyPet(){
        return "user/userBuyPet";
    }
    //跳转寄养的宠物
    @GetMapping("/userUploadPet")
    public String userUploadPet(){
        return "user/userUploadPet";
    }
    //跳转用户出售宠物的详情页面
    @GetMapping("/userShopPet")
    public String userShopPet(){
        return "user/userShopPet";
    }

    //退出
    @GetMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession(false).removeAttribute("userId");
        return "redirect:/";
    }
    //注册页面
    @GetMapping("/register")
    public String register(HttpServletRequest request){
        return "user/userRegister";
    }
}
