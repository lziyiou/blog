package com.ziyiou.service.impl;

import com.ziyiou.po.Category;
import com.ziyiou.repository.CategoryRepository;
import com.ziyiou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyiou
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Page<Category> listCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> listCategory() {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        return categoryRepository.findAll(sort);
    }

    @Override
    public List<Category> listTop(Integer topSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, topSize, sort);
        return categoryRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Category updateCategory(Long id, Category category) {
        // 查询数据是否存在
        Optional<Category> one = categoryRepository.findById(id);
        // 如果数据不存在，则抛出一个未找到的异常
        if (one.isEmpty()) {
            // TODO 抛出一个未查到的异常
            System.out.println("被操作的数据不存在！");
            return null;
        }
        // 数据存在，则进行更新
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
