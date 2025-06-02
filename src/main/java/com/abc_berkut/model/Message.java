package com.abc_berkut.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;
}
