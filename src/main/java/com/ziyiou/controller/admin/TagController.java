package com.ziyiou.controller.admin;

import com.ziyiou.po.Tag;
import com.ziyiou.service.TagService;
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
@Controller("adminTagController")
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 分页查询标签信息
     *
     * @param pageable 分页信息
     * @param model    传回页面的数据
     * @return 标签页面
     */
    @GetMapping("")
    public String list(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "/admin/tag";
    }

    @GetMapping("/input")
    public String inputPage(Model model) {
        model.addAttribute("tag", new Tag());
        return "/admin/tag-release";
    }

    @PostMapping("")
    public String input(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        // 检查是否已经存在
        Tag tagInData = tagService.getByName(tag.getName());
        if (tagInData != null) {
            result.rejectValue("name", "nameError", "标签已经存在");
        }
        // 参数校验
        if (result.hasErrors()) {
            return "/admin/tag-release";
        }
        // 插入新标签
        Tag saveTag = tagService.saveTag(tag);
        // 插入失败
        if (saveTag == null) {
            // TODO 抛出一个未成功的异常
            return "redirect:/admin/input";
        }
        attributes.addFlashAttribute("message", "新增成功");
        return "redirect:/admin/tag";
    }

    @PostMapping("/{id}")
    public String input(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        // 检查是否已经存在
        Tag tagInData = tagService.getByName(tag.getName());
        if (tagInData != null) {
            result.rejectValue("name", "nameError", "标签已经存在");
        }
        // 参数校验
        if (result.hasErrors()) {
            return "/admin/tag-release";
        }
        // 插入新标签
        Tag saveTag = tagService.updateTag(id, tag);
        // 插入失败
        if (saveTag == null) {
            // TODO 抛出一个未成功的异常
            System.out.println("插入失败");
            return "redirect:/admin/input";
        }
        attributes.addFlashAttribute("message", "更新成功");
        return "redirect:/admin/tag";
    }

    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "/admin/tag-release";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tag";
    }

}
