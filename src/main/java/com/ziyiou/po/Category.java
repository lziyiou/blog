package com.ziyiou.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyiou
 */
@Getter
@Setter
@Entity
public class Category {
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 分类名
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;

    /**
     * 分类下所有博客
     */
    @OneToMany(mappedBy = "category")
    private List<Blog> blogs = new ArrayList<>();
}
