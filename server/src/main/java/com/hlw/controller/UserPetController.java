package com.hlw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hlw.entity.Pet;
import com.hlw.entity.UserOpration;
import com.hlw.entity.UserPet;
import com.hlw.entity.UserUploadPet;
import com.hlw.mapper.PetMapper;
import com.hlw.mapper.UserOprationMapper;
import com.hlw.mapper.UserPetMapper;
import com.hlw.utils.ZZYUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/userPet")
public class UserPetController {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserPetMapper userPetMapper;

    @Autowired
    private UserOprationMapper userOprationMapper;
    //添加寄养宠物
    @PostMapping("/addPet")
    @ResponseBody
    @Transactional
    public Boolean userAddPet(Pet pet, MultipartFile file, HttpServletRequest request){
        int isOk;
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        pet.setUserId(userId);
        //文件上传
        String remotePath = ZZYUtil.getRemotePath(file);
        pet.setImgPath(remotePath);
        pet.setPetPrice(new BigDecimal(0));
        pet.setIsShop(false);
        isOk = petMapper.insert(pet);
        UserPet userPet = new UserPet();
        userPet.setUserId(userId);
        userPet.setPetId(pet.getPetId());
        isOk = userPetMapper.insert(userPet);
        return isOk != -1 ? true : false;
    }
    //获取用户寄养宠物
    @GetMapping("/userUploadPet")
    @ResponseBody
    public List<UserUploadPet> userUploadPet(HttpServletRequest request){
        Integer userId = (Integer) request.getSession(false).getAttribute("userId");
        return userPetMapper.userUploadPet(userId);
    }
    //通过id删除记录
    @DeleteMapping("/delUploadPet/{upId}")
    @ResponseBody
    @Transactional
    public Boolean delUploadPet(@PathVariable("upId") Integer upId){
        UserPet userPet = userPetMapper.selectById(upId);

        QueryWrapper<UserOpration> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userPet.getUserId()).eq("pet_id",userPet.getPetId());
        userOprationMapper.delete(wrapper);

        int i = userPetMapper.deleteById(upId);
        return i != -1 ? true : false;
    }
}
