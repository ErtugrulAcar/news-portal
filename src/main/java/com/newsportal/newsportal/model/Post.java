package com.newsportal.newsportal.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private boolean verified;

    @ManyToOne
    private PostGroup postGroup;

    @CreationTimestamp
    private LocalDateTime created_at;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isVerified() {
        return verified;
    }

    public PostGroup getPostGroup() {
        return postGroup;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }


    /**
     * Setters
     */

    public Post setId(int id) {
        this.id = id;
        return this;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public Post setVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public Post setPostGroup(PostGroup postGroup) {
        this.postGroup = postGroup;
        return this;
    }

    public Post setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
        return this;
    }
}
