package com.ziyiou.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ziyiou
 */
@Getter
@Setter
@Entity
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 用户创建日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 用户更新日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 作者写的所有博客
     */
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
