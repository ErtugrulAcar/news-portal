package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.Post;
import com.newsportal.newsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
