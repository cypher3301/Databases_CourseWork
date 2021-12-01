package com.spring.post.repository;

import com.spring.post.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);
}
