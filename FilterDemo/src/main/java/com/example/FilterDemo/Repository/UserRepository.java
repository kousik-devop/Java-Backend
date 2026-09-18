package com.example.FilterDemo.Repository;

import com.example.FilterDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
