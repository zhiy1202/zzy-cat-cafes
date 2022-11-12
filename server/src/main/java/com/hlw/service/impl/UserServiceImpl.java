package com.hlw.service.impl;

import com.hlw.entity.User;
import com.hlw.mapper.UserMapper;
import com.hlw.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
