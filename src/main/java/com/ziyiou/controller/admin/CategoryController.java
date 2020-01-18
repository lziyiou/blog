package com.ziyiou.controller.admin;

import com.ziyiou.po.Category;
import com.ziyiou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author ziyiou
 */
@Controller("adminCategoryController")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询分类信息
     *
     * @param pageable 分页信息
     * @param model    传回页面的数据
     * @return 分类页面
     */
    @GetMapping
    public String list(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", categoryService.listCategory(pageable));
        return "/admin/category";
    }

    @GetMapping("/input")
    public String inputPage(Model model) {
        model.addAttribute("category", new Category());
        return "/admin/category-release";
    }

    @PostMapping
    public String input(@Valid Category category, BindingResult result, RedirectAttributes attributes) {
        // 检查是否已经存在
        Category categoryInData = categoryService.getByName(category.getName());
        if (categoryInData != null) {
            result.rejectValue("name", "nameError", "分类已经存在");
        }
        // 参数校验
        if (result.hasErrors()) {
            return "/admin/category-release";
        }
        // 插入新分类
        Category saveCategory = categoryService.saveCategory(category);
        // 插入失败
        if (saveCategory == null) {
            // TODO 抛出一个未成功的异常
            return "redirect:/admin/input";
        }
        attributes.addFlashAttribute("message", "新增成功");
        return "redirect:/admin/category";
    }

    @PostMapping("/{id}")
    public String input(@Valid Category category, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        // 检查是否已经存在
        Category categoryInData = categoryService.getByName(category.getName());
        if (categoryInData != null) {
            result.rejectValue("name", "nameError", "分类已经存在");
        }
        // 参数校验
        if (result.hasErrors()) {
            return "/admin/category-release";
        }
        // 插入新分类
        Category saveCategory = categoryService.updateCategory(id, category);
        // 插入失败
        if (saveCategory == null) {
            // TODO 抛出一个未成功的异常
            System.out.println("插入失败");
            return "redirect:/admin/input";
        }
        attributes.addFlashAttribute("message", "更新成功");
        return "redirect:/admin/category";
    }

    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "/admin/category-release";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        categoryService.deleteCategory(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/category";
    }

}
