package com.ziyiou.controller.admin;

import com.ziyiou.po.Blog;
import com.ziyiou.po.Category;
import com.ziyiou.po.Tag;
import com.ziyiou.po.User;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ziyiou
 */
@Controller("adminBlogController")
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    /**
     * 博客管理首页面
     *
     * @param pageable 分页信息
     * @param model    返回数据
     * @return admin/blog.html
     */
    @GetMapping
    public String adminBlog(@PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        // 加载分类
        model.addAttribute("categories", categoryService.listCategory());

        // 查询博客页面
        model.addAttribute("page", blogService.listBlog(pageable));
        return "/admin/blog";
    }

    /**
     * 博客首页按条件查询
     *
     * @param pageable   分页信息
     * @param blog       查询条件之博客相关
     * @param categoryId 查询条件之分类
     * @param model      返回数据
     * @return /admin/blog的blogList片段
     */
    @PostMapping("/search")
    public String search(@PageableDefault(sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Blog blog,
                         Long categoryId,
                         Model model) {
        // 查询博客页面
        Category category = new Category();
        category.setId(categoryId);
        blog.setCategory(category);
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        // 返回片段
        return "/admin/blog::blogList";
    }

    /**
     * 博客新增页面
     *
     * @param model 返回的数据
     * @return /admin/blog-release.html
     */
    @GetMapping("/input")
    public String release(Model model) {
        // 加载分类
        model.addAttribute("categories", categoryService.listCategory());
        // 加载标签
        model.addAttribute("tags", tagService.listTag());

        Blog blog = new Blog();
        blog.setCategory(new Category());
        model.addAttribute("blog", blog);
        return "/admin/blog-release";
    }

    /**
     * 对已有博客的编辑页面
     *
     * @param id    要编辑的博客的id
     * @param model 返回的数据
     * @return /admin/blog-release.html
     */
    @GetMapping("/{id}/input")
    public String edit(@PathVariable Long id, Model model) {
        // 加载分类
        model.addAttribute("categories", categoryService.listCategory());
        // 加载标签
        model.addAttribute("tags", tagService.listTag());

        Blog blogInData = blogService.getById(id);
        model.addAttribute("blog", blogInData);

        model.addAttribute("tagsFromBlog", longListToString(getTagsIds(blogInData.getTags())));

        return "/admin/blog-release";
    }

    /**
     * 博客的新增
     *
     * @param blog       写好的博客
     * @param categoryId 该博客的分类
     * @param tagIds     该博客的标签
     * @param session    用于取出作者信息
     * @param attributes 返回操作状态（成功、失败）
     * @return /admin/blog
     */
    @PostMapping
    public String release(Blog blog, Long categoryId, String tagIds, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setCategory(categoryService.getCategory(categoryId));
        blog.setTags(tagService.getTags(stringToLongList(tagIds)));

        Blog blogInData;
        if (blog.getId() == null) {
            blogInData = blogService.save(blog);
        } else {
            blogInData = blogService.update(blog);
        }

        if (blogInData == null) {
            attributes.addFlashAttribute("message", "操作失败");
        }
        attributes.addFlashAttribute("message", "操作成功");
        return "redirect:/admin/blog";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.delete(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blog";
    }

    /**
     * String字符串转成List<Long>数据格式
     * String str = "1,2,3,4,5,6" -> List<Long> listLong [1,2,3,4,5,6];
     *
     * @param strArr 字符串
     * @return Long类型列表
     */
    private List<Long> stringToLongList(String strArr) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(strArr)) {
            String[] ids = strArr.split(",");
            for (String id : ids) {
                list.add(Long.valueOf(id));
            }
        }
        return list;
    }

    /**
     * String字符串转成List<Long>数据格式
     * String str = "1,2,3,4,5,6" -> List<Long> listLong [1,2,3,4,5,6];
     *
     * @param list Long类型列表
     * @return 字符串
     */
    private String longListToString(List<Long> list) {
        List<String> stringList = list.stream().map(String::valueOf).collect(Collectors.toList());
        return String.join(",", stringList);
    }

    /**
     * 把tagList中的标签id取出放到LongList中
     *
     * @param tagList 标签列表
     * @return LongList
     */
    private List<Long> getTagsIds(List<Tag> tagList) {
        List<Long> longList = new ArrayList<>();
        tagList.forEach(tag -> longList.add(tag.getId()));
        return longList;
    }
}
