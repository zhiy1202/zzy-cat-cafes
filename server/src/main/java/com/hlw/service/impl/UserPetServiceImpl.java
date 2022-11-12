package com.hlw.service.impl;

import com.hlw.entity.UserPet;
import com.hlw.mapper.UserPetMapper;
import com.hlw.service.IUserPetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hlw
 * @since 2022-09-09
 */
@Service
public class UserPetServiceImpl extends ServiceImpl<UserPetMapper, UserPet> implements IUserPetService {

}
