package com.zhehao.fishing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("posts")
public class PostEntity {

    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    private String postTitle;

    private String postText;

    private Long authorId;

    private Timestamp postPublishTime;

    private int likesNum;

    private int commentsNum;

}
