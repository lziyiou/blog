package com.ziyiou.service;

import com.ziyiou.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author ziyiou
 */
public interface BlogService {
    /**
     * 获取博客页面
     * @param pageable 页面信息
     * @param blog 查询条件
     * @return 查询到的页面
     */
    Page<Blog> listBlog(Pageable pageable, Blog blog);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listByCategory(Pageable pageable, Long categoryId);
    Page<Blog> listByTag(Pageable pageable, Long tagId);
    Blog save(Blog blog);
    Blog update(Blog blog);
    Blog getById(Long id);
    void delete(Long id);
    Page<Blog> listTopRecommend();

    /**
     * 博客访问，访问计数器+1
     */
    void blogView(Long id);
}
