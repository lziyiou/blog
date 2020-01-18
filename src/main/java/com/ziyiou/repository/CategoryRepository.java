package com.ziyiou.repository;

import com.ziyiou.po.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyiou
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    @Override
    Optional<Category> findById(Long id);

    @Query("select c from Category c")
    List<Category> findTop(Pageable pageable);

    @Override
    @Query("select c from Category c")
    List<Category> findAll(Sort var1);
}
