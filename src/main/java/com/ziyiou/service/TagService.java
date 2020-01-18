package com.ziyiou.service;

import com.ziyiou.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ziyiou
 */
public interface TagService {
    /**
     * 保存
     * @param tag 标签
     * @return 成功则返回，否则返回null
     */
    Tag saveTag(Tag tag);

    /**
     * tag
     * @param id id
     * @return 查到的标签
     */
    Tag getTag(Long id);

    List<Tag> getTags(List<Long> id);

    /**
     * 分页查询tag
     * @param pageable 分页对象（分页条件）
     * @return 分页信息
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 查询tag
     * @return tag信息
     */
    List<Tag> listTag();

    /**
     * 查询tag top
     * @param topSize TOP size
     * @return 返回TOP标签信息
     */
    List<Tag> listTop(Integer topSize);

    /**
     * 更新category
     * @param tag 新的标签
     * @return 更新成功则返回tag，否则返回Null
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 跟据id删除tag
     * @param id id
     */
    void deleteTag(Long id);
    Tag getByName(String name);

}
