package com.example.springapp.repository;

import com.example.springapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "select * from \"user\" where \"user\".id != :loggedUserId", nativeQuery = true)
    List<User> getAllExceptLoggedUser(@Param("loggedUserId") Long loggedUserId);
}
