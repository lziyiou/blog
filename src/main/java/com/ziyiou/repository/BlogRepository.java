package com.ziyiou.repository;

import com.ziyiou.po.Blog;
import com.ziyiou.po.Category;
import com.ziyiou.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ziyiou
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    Page<Blog> findTop6ByRecommendedTrue(Pageable pageable);
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByTags(Tag tag, Pageable pageable);
    @Query("select b from Blog b where b.id = ?1")
    Page<Blog> findPageById(Long id, Pageable pageable);
}
