package com.zhehao.fishing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("post_likes")
public class PostLikeEntity implements Serializable {

    @TableId(value = "like_id", type = IdType.AUTO)

    private Long likeId;

    private Timestamp likeTime;

    private Long userId;

    private Long postId;
}
