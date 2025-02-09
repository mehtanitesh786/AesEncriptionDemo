package com.encryption.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encryption.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {

}
