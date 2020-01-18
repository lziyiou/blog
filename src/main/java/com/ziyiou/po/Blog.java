package com.ziyiou.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ziyiou
 */

@Setter
@Getter
@Entity
public class Blog {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "博客标题不能为空")
    private String title;

    /**
     * 内容
     */
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    /**
     * 首图
     */
    private String firstPicture;

    /**
     * 转载、原创等标记
     */
    private String flag;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 概要
     */
    private String description;

    /**
     * 赞赏开启
     */
    private Boolean admirable;

    /**
     * 评论开启
     */
    private Boolean commentable;

    /**
     * 发布
     */
    private Boolean published;

    /**
     * 是否推荐
     */
    private Boolean recommended;

    /**
     * 创建日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 更新日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 分类
     */
    @ManyToOne
    private Category category;

    /**
     * 标签
     */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    /**
     * 作者
     */
    @ManyToOne
    private User user;

    /**
     * 评论
     */
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
