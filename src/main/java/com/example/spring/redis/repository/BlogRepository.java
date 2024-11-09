package com.example.spring.redis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.redis.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
  List<Blog> findByPublished(boolean published);

  List<Blog> findByTitleContaining(String title);
}
