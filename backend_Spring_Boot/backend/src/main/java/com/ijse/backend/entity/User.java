package com.ijse.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users") //change Table name to Users (Default table name : User)
@Data //setters and getters using Lombok
public class User {
    @Id //Key field
    @GeneratedValue(strategy = GenerationType.IDENTITY) //primary key will be incremented automatically.
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; //customer, admin
}