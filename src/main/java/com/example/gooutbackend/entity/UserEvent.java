package com.example.gooutbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_event")
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "will_come")
    private Boolean willCome;

    @Column(name = "creator")
    private Boolean creator;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_playground_event",
            joinColumns = @JoinColumn(name = "user_event_id"),
            inverseJoinColumns = @JoinColumn(name = "playground_event_id")
    )
    private Set<PlaygroundEvent> playgroundEvents;
}
