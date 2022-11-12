package com.hlw.controller;

import com.hlw.entity.UserBuy;
import com.hlw.entity.UserBuyPet;
import com.hlw.mapper.PetMapper;
import com.hlw.mapper.UserBuyMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/buy")
public class UserBuyController {
    @Autowired
    private UserBuyMapper userBuyMapper;
    @Autowired
    private PetMapper petMapper;

    //购买宠物
    @GetMapping("/buyCat/{petId}")
    public String buyCat(@PathVariable("petId") Integer petId, HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        UserBuy userBuy = new UserBuy();
        userBuy.setUserId(userId);
        userBuy.setPetId(petId);

        int insert = userBuyMapper.insert(userBuy);
        //删除宠物
        petMapper.deleteById(petId);
        return "user/index";
    }

    //查询用户购买的所有宠物
    @GetMapping("/userBuyPet")
    @ResponseBody
    public List<UserBuyPet> userBuyPet(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        return userBuyMapper.userBuyPet(userId);
    }
    //删除记录
    @DeleteMapping("/delUserBuy/{ubId}")
    @ResponseBody
    public Boolean delUserBuy(@PathVariable("ubId") Integer ubId){
        int i = userBuyMapper.deleteById(ubId);
        return i != -1 ? true : false;
    }

    //购买数量
    @GetMapping("/buyCount")
    @ResponseBody
    public Integer buyCount(){
        return userBuyMapper.selectCount(null);
    }

}
