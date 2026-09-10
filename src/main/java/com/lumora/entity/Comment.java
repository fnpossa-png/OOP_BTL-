package com.lumora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // tao cot content luu tru noi dung van ban cua cmt
    @Column(nullable = false, length = 1000) // tao cot ko duoc trong , do dai 1000
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // N-cmt : 1-photo
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // tao cot thoi gian cmt
    @Column(name = "created_at", updatable = false) // khi chinh sua cmt ko thay doi time
    private LocalDateTime createdAt;

    protected Comment(){
        // de JPA tao khung doi tuong rong
    }

    public Comment(String content, Photo photo, User user){
        this.content = content;
        this.photo = photo;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    // Noi de sua noi dung
    public void editContent(String newContent){
        if(newContent == null || newContent.isBlank()){
            throw new IllegalArgumentException("Hãy bình luận gì đó");
        }
        this.content = newContent;
    }
}
