package com.ziyiou.service.impl;

import com.ziyiou.po.Category;
import com.ziyiou.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void saveCategory() {
        Category category = new Category();
        category.setName("要改的内容");
        categoryService.saveCategory(category);
    }

    @Test
    void updateCategory() {
        Category category = new Category();
        category.setId(4L);
        category.setName("要被删的内容");
        categoryService.updateCategory(4L, category);
    }

    @Test
    void deleteCategory(){
        categoryService.deleteCategory(3L);
    }
}