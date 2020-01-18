package com.ziyiou.controller;

import com.ziyiou.service.BlogService;
import com.ziyiou.service.CategoryService;
import com.ziyiou.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ziyiou
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public String index(@PageableDefault(size = 6, direction = Sort.Direction.DESC, sort = "createTime") Pageable pageable, Model model) {
        // 博客页面
        model.addAttribute("page", blogService.listBlog(pageable));
        // 分类TOP6
        model.addAttribute("categories", categoryService.listTop(6));
        // 标签TOP12
        model.addAttribute("tags", tagService.listTop(12));
        // 最新推荐
        model.addAttribute("recommends", blogService.listTopRecommend());

        return "/index";
    }

}
