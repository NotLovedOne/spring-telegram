package com.abc_berkut.repository;

import com.abc_berkut.model.Message;
import com.abc_berkut.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUser(User user);
}
