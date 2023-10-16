package com.zhehao.fishing.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("fishing_methods")
public class SkillEntity {

    @TableId(value = "fishing_method_id", type = IdType.AUTO)
    private Long fishingMethodId;

    private String fishMethodName;

    private String fishMethodImages;

    private String fishMethodDescription;

    private String targetSpecies;

}
