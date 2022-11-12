package com.hlw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hlw.entity.Pet;
import com.hlw.entity.UserPet;
import com.hlw.mapper.PetMapper;
import com.hlw.utils.ZZYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetMapper petMapper;
    @PostMapping("/addPet")
    @ResponseBody
    public Boolean userAddPet(Pet pet, MultipartFile file, HttpServletRequest request){
        int isOk;
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        pet.setUserId(userId);
        //文件上传
        String remotePath = ZZYUtil.getRemotePath(file);
        pet.setImgPath(remotePath);
        pet.setIsShop(true);
        isOk = petMapper.insert(pet);
        return isOk != -1 ? true : false;
    }
    //通过petId获取宠物信息
    @GetMapping("/lookDetail/{petId}")
    public String petDetail(@PathVariable("petId") Integer petId, Model model){
        Pet pet = petMapper.selectById(petId);
        model.addAttribute("pet",pet);
        return "user/lookDetail";
    }

    //通过userId获取所有出售的宠物
    @GetMapping("/mySell")
    @ResponseBody
    public List<Pet> mySell(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        return petMapper.mySell(userId);
    }
    //通过id 删除
    @DeleteMapping("/delPet/{petId}")
    @ResponseBody
    public Boolean delPet(@PathVariable("petId") Integer petId){
        int i = petMapper.deleteById(petId);
        return i != -1 ? true:false;
    }

    //通过id修改
    @PutMapping("/changePetInfo")
    @ResponseBody
    public Boolean changePetInfo(Pet pet){
        int i = petMapper.updateById(pet);
        return i != -1 ? true:false;
    }

    //通过名字搜索
    @GetMapping("/searchPet")
    public String searchPet(String petName,Model model){
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.like("pet_name",petName);

        List<Pet> petList = petMapper.selectList(wrapper);
        model.addAttribute("petList",petList);
        return "user/index";
    }

    //宠物数量
    @GetMapping("/petCount")
    @ResponseBody
    public Integer petCount(){
        return petMapper.selectCount(null);
    }

    //获取所有出售宠物信息
    @GetMapping("/allSellPet")
    @ResponseBody
    public List<Pet> allPet(){
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.eq("is_shop",true);
        return petMapper.selectList(wrapper);
    }

    //获取所有寄售宠物信息
    @GetMapping("/allUploadPet")
    @ResponseBody
    public List<Pet> allUploadPet(){
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.eq("is_shop",false);
        return petMapper.selectList(wrapper);
    }
}
