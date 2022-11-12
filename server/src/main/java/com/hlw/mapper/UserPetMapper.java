package com.hlw.mapper;

import com.hlw.entity.UserPet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlw.entity.UserUploadPet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hlw
 * @since 2022-09-09
 */
@Mapper
public interface UserPetMapper extends BaseMapper<UserPet> {
    //获得用户所有寄养的宠物
    @Select("select up.up_id , p.pet_id , p.pet_name , p.pet_state , p.pet_price ,up.create_time from t_pet p join t_user_pet up on p.pet_id = up.pet_id where up.user_id = #{userId}")
    List<UserUploadPet> userUploadPet(Integer userId);
}
