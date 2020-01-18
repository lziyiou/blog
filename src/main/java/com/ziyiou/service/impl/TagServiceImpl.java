package com.ziyiou.service.impl;

import com.ziyiou.po.Tag;
import com.ziyiou.repository.TagRepository;
import com.ziyiou.service.TagService;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public List<Tag> getTags(List<Long> ids) {
        return tagRepository.findAllById(ids);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        return tagRepository.findAll(sort);
    }

    @Override
    public List<Tag> listTop(Integer topSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, topSize, sort);
        return tagRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag category) {
        // 查询数据是否存在
        Optional<Tag> one = tagRepository.findById(id);
        // 如果数据不存在，则抛出一个未找到的异常
        if (one.isEmpty()) {
            // TODO 抛出一个未查到的异常
            System.out.println("被操作的数据不存在！");
            return null;
        }
        // 数据存在，则进行更新
        return tagRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Tag getByName(String name) {
        return tagRepository.findByName(name);
    }
}
