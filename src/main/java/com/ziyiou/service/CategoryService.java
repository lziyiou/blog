package com.ziyiou.service;

import com.ziyiou.po.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ziyiou
 */
public interface CategoryService {
    /**
     * 保存
     * @param category 分类
     * @return 成功则返回，否则返回null
     */
    Category saveCategory(Category category);

    /**
     * 通过id查询category
     * @param id id
     * @return 查到的分类
     */
    Category getCategory(Long id);

    /**
     * 分页查询category
     * @param pageable 分页对象（分页条件）
     * @return 分页信息
     */
    Page<Category> listCategory(Pageable pageable);

    /**
     * 查询category
     * @return 反有分类信息
     */
    List<Category> listCategory();

    /**
     * 查询category top
     * @param topSize TOP size
     * @return 返回TOP分类信息
     */
    List<Category> listTop(Integer topSize);

    /**
     * 更新category
     * @param category 新的分类
     * @return 更新成功则返回category，否则返回Null
     */
    Category updateCategory(Long id, Category category);

    /**
     * 跟据id删除category
     * @param id id
     */
    void deleteCategory(Long id);
    Category getByName(String name);

}
