package com.hlw.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zzy
 * @desc
 */
@Data
public class UserBuyPet {
    private Integer ubId;
    private Integer petId;
    private String petName;
    private BigDecimal petPrice;
    private LocalDateTime createTime;
}
