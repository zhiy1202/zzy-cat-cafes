package com.hlw.mapper;

import com.hlw.entity.UserBuyPet;
import com.hlw.entity.UserOpration;
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
public interface UserOprationMapper extends BaseMapper<UserOpration> {


}
