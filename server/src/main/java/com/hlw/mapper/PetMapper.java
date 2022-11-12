package com.hlw.mapper;

import com.hlw.entity.Pet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface PetMapper extends BaseMapper<Pet> {
//通过 userId 查询所有出售宠物
    @Select("select pet_id, pet_name , pet_age , pet_weight , pet_type, pet_price ,pet_intro   from t_pet where is_shop = 1 and is_del = 0 and user_id = #{userId}")
    List<Pet> mySell(Integer userId);
}
