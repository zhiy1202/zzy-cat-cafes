package com.hlw.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hlw
 * @since 2022-09-09
 */
@Getter
@Setter
@TableName("t_pet")
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pet_id", type = IdType.AUTO)
    private Integer petId;

    private String petName;

    private Integer petAge;

    private Double petWeight;

    private String petType;

    private Boolean petGender;

    private BigDecimal petPrice;

    private String petIntro;

    private String petLife;

    private String petState;

    private Boolean isShop;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDel;

    private String imgPath;

    private Integer userId;


}
