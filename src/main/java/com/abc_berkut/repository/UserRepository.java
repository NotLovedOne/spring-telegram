package com.abc_berkut.repository;

import com.abc_berkut.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByTelegramToken(String telegramToken);
}
