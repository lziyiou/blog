package com.ziyiou.controller;

import com.ziyiou.po.Blog;
import com.ziyiou.service.BlogService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ziyiou
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        // 通过id获取博客
        Blog blog = blogService.getById(id);
        //TODO 把浏览次数做个+1操作
        blogService.blogView(id);

        // 把博客内容转换为markdown格式，方便前台页面展示
        blog.setContent(markdownToHtml(blog.getContent()));

        model.addAttribute("blogPage", blog);

        return "/blog";
    }

    private static String markdownToHtml(String md) {
        Parser parser = Parser.builder().build();
        Node parse = parser.parse(md);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(parse);
    }
}
