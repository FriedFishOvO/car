package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "users")
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long loginId;

    private String nickname;

    private String username;

    private String password;

    private Integer type;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}