package com.example.demotask.model;

import com.example.demotask.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Task extends BaseClass{
    private String title;
    private String description;
    private LocalDateTime completedAt;
    private Status status;
    @ManyToOne
    private User user;
}
