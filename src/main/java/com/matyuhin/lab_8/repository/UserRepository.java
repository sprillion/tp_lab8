package com.matyuhin.lab_8.repository;

import com.matyuhin.lab_8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
