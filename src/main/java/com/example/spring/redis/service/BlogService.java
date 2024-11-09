package com.example.spring.redis.service;

import com.example.spring.redis.repository.BlogRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.example.spring.redis.model.Blog;

@Service
@EnableCaching
public class BlogService {

  @Autowired BlogRepository blogRepository;

  @Cacheable("blogs")
  public List<Blog> findAll() {
    doLongRunningTask();

    return blogRepository.findAll();
  }

  @Cacheable("blogs")
  public List<Blog> findByTitleContaining(String title) {
    doLongRunningTask();

    return blogRepository.findByTitleContaining(title);
  }

  @Cacheable("blog")
  public Optional<Blog> findById(long id) {
    doLongRunningTask();

    return blogRepository.findById(id);
  }

  public Blog save(Blog blog) {
    return blogRepository.save(blog);
  }

  @CacheEvict(value = "blog", key = "#blog.id")
  public Blog update(Blog blog) {
    return blogRepository.save(blog);
  }

  @CacheEvict(value = "blog", key = "#id")
  public void deleteById(long id) {
    blogRepository.deleteById(id);
  }

  @CacheEvict(value = { "blog", "blogs", "published_blogs" }, allEntries = true)
  public void deleteAll() {
    blogRepository.deleteAll();
  }

  @Cacheable("published_blogs")
  public List<Blog> findByPublished(boolean isPublished) {
    doLongRunningTask();

    return blogRepository.findByPublished(isPublished);
  }

  private void doLongRunningTask() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
