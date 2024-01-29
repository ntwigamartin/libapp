package com.mcmillan.libapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcmillan.libapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
