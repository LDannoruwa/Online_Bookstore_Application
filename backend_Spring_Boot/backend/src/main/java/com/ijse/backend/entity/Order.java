package com.ijse.backend.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data //lombok getters and setters
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false) //is not allowed to update
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Double totalAmount;

    //(Many) orders ---<FK>--- books(Many) [need to implement in both sides]
    @ManyToMany(cascade = CascadeType.ALL)
    //create intermediate table in ManyToMany relationship
    @JoinTable(
        name = "orders_books",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> book = new HashSet<>();

    //(Many|FK) orders ----- customer(one)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id") //FK: foreign key
    private Customer customer;


    //--------[Start: methods related to the createdAt & updatedAt]--------------------
    @PrePersist //before something create in database
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate //before something update in database
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
    //--------[End: methods related to the createdAt & updatedAt]----------------------
}
