package com.demo.blog.repository;

import com.demo.blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findByUserName(String userName);

    Optional<User> findByToken(String token);

}
