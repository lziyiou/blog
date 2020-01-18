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
public class Comment {

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
     * 邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 评论所属博客
     */
    @ManyToOne
    private Blog blog;

    /**
     * 子评论
     */
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    /**
     * 父评论
     */
    @ManyToOne
    private Comment parentComment;
}
