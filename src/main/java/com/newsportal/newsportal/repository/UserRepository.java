package com.newsportal.newsportal.repository;

import com.newsportal.newsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT user.group.id FROM User AS user WHERE user.id= :userId")
    Integer findGroupIdByUserId(int userId);
}
