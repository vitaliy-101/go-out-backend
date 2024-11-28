package com.example.gooutbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "playground_event")
public class PlaygroundEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "general_collection")
    private Boolean generalCollection;

    @ManyToOne
    @JoinColumn(name = "playground_id")
    private Playground playground;

    @ManyToMany(mappedBy = "playgroundEvents")
    private Set<UserEvent> userEvents = new HashSet<>();

    public void addUserEvent(UserEvent userEvent) {
        userEvents.add(userEvent);
    }


}
