package com.hlw.service.impl;

import com.hlw.entity.Pet;
import com.hlw.mapper.PetMapper;
import com.hlw.service.IPetService;
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
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {

}
