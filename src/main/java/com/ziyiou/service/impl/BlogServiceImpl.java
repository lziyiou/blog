package com.ziyiou.service.impl;

import com.ziyiou.po.Blog;
import com.ziyiou.po.Category;
import com.ziyiou.po.Tag;
import com.ziyiou.repository.BlogRepository;
import com.ziyiou.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author ziyiou
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != blog.getTitle() && !"".equals(blog.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + blog.getTitle() + "%"));
            }
            if (null != blog.getCategory() && null != blog.getCategory().getId()) {
                predicates.add(criteriaBuilder.equal(root.<String>get("category").get("id"), blog.getCategory().getId()));
            }
            if (null != blog.getRecommended() && blog.getRecommended()) {
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommended"), blog.getRecommended()));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return null;
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listByCategory(Pageable pageable, Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return blogRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Blog> listByTag(Pageable pageable, Long tagId) {
        Tag tag = new Tag();
        tag.setId(tagId);
        return blogRepository.findAllByTags(tag, pageable);
    }

    @Transactional
    @Override
    public Blog save(Blog blog) {
        // 初始化
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog update(Blog blog) {
        // 初始化
        Optional<Blog> blogInDataOptional = blogRepository.findById(blog.getId());
        if (blogInDataOptional.isEmpty()) {
            //TODO 抛一个不存在异常
            System.out.println("该博客不存在");
            return null;
        } else {
            Blog blogInData = blogInDataOptional.get();
            BeanUtils.copyProperties(blog, blogInData, getNullField(blog));
            blogInData.setUpdateTime(new Date());
            return blogRepository.save(blogInData);
        }
    }

    /**
     * 获取属性中为空的字段
     *
     * @param target 要找的对象
     * @return 把对象中空的字段获取出来，返回一个String[]
     */
    private static String[] getNullField(Object target) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> notNullFieldSet = new HashSet<>();
        if (propertyDescriptors.length > 0) {
            for (PropertyDescriptor p : propertyDescriptors) {
                String name = p.getName();
                Object value = beanWrapper.getPropertyValue(name);
                if (Objects.isNull(value)) {
                    notNullFieldSet.add(name);
                }
            }
        }
        String[] notNullField = new String[notNullFieldSet.size()];
        return notNullFieldSet.toArray(notNullField);
    }

    @Override
    public Blog getById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isEmpty()) {
            return null;
        }
        return blog.get();
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> listTopRecommend() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "updateTime"));
        return blogRepository.findTop6ByRecommendedTrue(pageable);
    }

    @Override
    public void blogView(Long id) {
        Blog blog = getById(id);
        blog.setViews(blog.getViews()+1);
        blogRepository.save(blog);
    }

}
