package com.ziyiou.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyiou
 */
@Getter
@Setter
@Entity
public class Tag {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 标签名
     */
    @NotBlank(message = "标签名称不能为空")
    private String name;

    /**
     * 标签下所有博客
     */
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
