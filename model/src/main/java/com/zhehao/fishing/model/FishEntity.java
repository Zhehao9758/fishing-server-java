package com.zhehao.fishing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fishes")
public class FishEntity {

    @TableId(value = "fish_id", type = IdType.AUTO)
    private Long fishId;
    private String fishName;
    private String fishImageUrls;
    private String food;
    private String habitat;
    private String fishDescription;

}

