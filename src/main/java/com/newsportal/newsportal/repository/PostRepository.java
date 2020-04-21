package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.Post;
import com.newsportal.newsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value="SELECT * FROM post WHERE verified= true ORDER BY created_at DESC LIMIT 5", nativeQuery=true)
    List<Post> findLast5PostsOrderByDescCreated_At();
}
