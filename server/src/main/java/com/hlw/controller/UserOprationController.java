package com.hlw.controller;

import com.hlw.entity.UserOpration;
import com.hlw.mapper.UserOprationMapper;
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
@RequestMapping("/userOpration")
public class UserOprationController {
    @Autowired
    private UserOprationMapper userOprationMapper;
    //添加操作
    @PostMapping("/addOpration")
    @ResponseBody
    public Boolean addOptation(HttpServletRequest request,UserOpration userOpration){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        userOpration.setUserId(userId);
        int i = userOprationMapper.insert(userOpration);
        return i != -1 ? true : false;
    }
    //用户操作数量
    @GetMapping("/oprationCount")
    @ResponseBody
    public Integer oprationCount(){
        return userOprationMapper.selectCount(null);
    }
    //所有操作
    @GetMapping("/allActive")
    @ResponseBody
    public List<UserOpration> allActive(){
        return userOprationMapper.selectList(null);
    }
    //通过id删除
    @DeleteMapping("/delActive/{uoId}")
    @ResponseBody
    public Boolean delActive(@PathVariable("uoId") Integer uoId){
        int i = userOprationMapper.deleteById(uoId);
        return i != -1 ? true:false;
    }
    //删除所有
    @DeleteMapping("/delAllActive")
    @ResponseBody
    public Boolean delAllActive(){
        int i = userOprationMapper.delete(null);
        return i != -1 ? true:false;
    }
}
