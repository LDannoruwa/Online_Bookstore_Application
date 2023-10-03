package com.ijse.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.backend.entity.User;
 
//JpaRepository : has full API for crud/paging/sorting
public interface UserRepository extends JpaRepository<User, Long>{
    //custom quaries can be added here

    //Generic quaries : no need to implement
    Optional<User> findByUsername(String username); //Optional<> :wrap data before returning: if it can potentially be null
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
