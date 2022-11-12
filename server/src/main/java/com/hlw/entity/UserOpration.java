package com.hlw.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("t_user_opration")
public class UserOpration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uo_id", type = IdType.AUTO)
    private Integer uoId;

    private Integer userId;

    private Integer petId;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
