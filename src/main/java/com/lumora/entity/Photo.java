package com.lumora.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
