package com.example.gooutbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "playground_prediction")
public class PlaygroundPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "user_count")
    private Integer userCount;

    @ManyToOne
    @JoinColumn(name = "playground_id")
    private Playground playground;

}
