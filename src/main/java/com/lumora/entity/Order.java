package com.lumora.entity;
// order giong nhu 1 chiec gio

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders") // trong sql co ham order by

@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String status;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orderitem> items = new ArrayList<>();

    protected Order(){

    }

    public Order(User user){
        this.user = user;
        this.status = "PENDING"; // khach vua dat
        this.createdAt = LocalDateTime.now();
    }

    // cua them don hang
    public void addItem(Orderitem item){
        items.add(item);
        item.setOrder(this);  // danh dau mon do thuoc vao cai gio
    }

    // cua chuyen trang thai khi thanh toan thanh cong
    public void markAsPaid(){
        this.status = "PAID";
    }

}
