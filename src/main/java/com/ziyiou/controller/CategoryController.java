package com.ziyiou.controller;

import com.ziyiou.service.BlogService;
import com.ziyiou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ziyiou
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    /**
     * 默认没有选择分类，将展示所有分类下博客
     * @param pageable 博客列表页面
     * @param model 返回数据
     * @return /category
     */
    @GetMapping
    public String category(@PageableDefault(size = 6, direction = Sort.Direction.DESC, sort = "createTime")Pageable pageable, Model model){

        model.addAttribute("categories", categoryService.listCategory());
        model.addAttribute("blogPage", blogService.listBlog(pageable));
        return "/category";
    }

    @GetMapping("/{id}")
    public String category(@PageableDefault(size = 6, direction = Sort.Direction.DESC, sort = "createTime")Pageable pageable,
                           @PathVariable("id") Long id,
                           Model model){
        model.addAttribute("categories", categoryService.listCategory());
        model.addAttribute("blogPage", blogService.listByCategory(pageable, id));
        model.addAttribute("categoryId", id);
        return "/category";
    }
}
