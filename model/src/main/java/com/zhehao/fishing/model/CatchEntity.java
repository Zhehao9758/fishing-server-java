package com.zhehao.fishing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;


@Data
@TableName("catches")
public class CatchEntity {

    @TableId(value = "catch_id", type = IdType.AUTO)
    private Long catchId;

    private String catchTitle;

    private String catchText;

    private Timestamp catchPublishTime;

    private String catchImageUrls;

    private Long userId;

    private Long likes = 0L;
}
