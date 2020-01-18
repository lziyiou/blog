package com.ziyiou.repository;

import com.ziyiou.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyiou
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    @Override
    Optional<Tag> findById(Long id);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

    @Override
    @Query("select t from Tag t")
    List<Tag> findAll(Sort var1);
}
