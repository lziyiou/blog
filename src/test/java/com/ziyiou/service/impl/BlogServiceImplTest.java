package com.ziyiou.service.impl;

import com.ziyiou.po.Blog;
import com.ziyiou.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    private BlogService blogService;

    @Test
    void listByCategory() {
        Page<Blog> blogPage = blogService.listByCategory(PageRequest.of(1, 2, Sort.Direction.DESC, "createTime"), 2L);
        for (Blog blog : blogPage.getContent()) {
            System.out.println("blog = " + blog);
        }

    }
}