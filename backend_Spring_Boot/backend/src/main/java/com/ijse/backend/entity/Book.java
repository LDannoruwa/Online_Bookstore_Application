package com.ijse.backend.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "books")
//---
@EqualsAndHashCode(exclude = "order") //to exclude order
//---
@Data //lombok getters and setters
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Long qoh;

    @Column(nullable = true)
    private String bookImageName; //store name of the image file

    //(Many | FK) books --- Categories(one)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") //FK: foreign key
    private Category category;
    
    //(Many) books --<FK>- orders(Many) [need to implement in both sides]
    @ManyToMany(mappedBy = "book")
    private Set<Order> order = new HashSet<>();
}
