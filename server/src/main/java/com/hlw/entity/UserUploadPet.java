package com.hlw.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zzy
 * @desc
 */
@Data
public class UserUploadPet {
    private Integer upId;
    private Integer petId;
    private String petName;
    private String petState;
    private LocalDateTime createTime;
}
