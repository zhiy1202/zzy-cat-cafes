package com.hlw.mapper;

import com.hlw.entity.UserBuy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlw.entity.UserBuyPet;
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
public interface UserBuyMapper extends BaseMapper<UserBuy> {

    //用户购买宠物页面
    @Select("select ub.ub_id , p.pet_id , p.pet_name , p.pet_price ,ub.create_time from t_pet p join t_user_buy ub on p.pet_id = ub.pet_id where ub.user_id = #{userId}")
    List<UserBuyPet> userBuyPet(Integer userId);
}
