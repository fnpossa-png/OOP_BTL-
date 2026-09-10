package com.lumora.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(
        name ="photo_like",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "photo_id"}
        )
)
@Getter
public class Photolike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // moi like phai co user
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="photo_id", nullable = false)
    private Photo photo;

    @Column(name = "createdAt", updatable = false)// ko ud neu thay doi
    private LocalDateTime createdAt;

    protected Photolike(){

    }
    public Photolike(User user, Photo photo){
        this.user = user;
        this.photo = photo;
        this.createdAt = LocalDateTime.now();
    }
}
