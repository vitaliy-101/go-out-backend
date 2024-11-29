package com.example.gooutbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "playground")
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "town")
    private String town;

    @Column(name = "street")
    private String street;

    @Column(name = "type")
    private String type;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "playground_state")
    private Integer playgroundState;

    @Column(name = "current_book_state")
    private Boolean currentBookState;

    @Column(name = "file_data")
    private byte[] fileData;

    @OneToMany(mappedBy = "playground")
    private Set<PlaygroundEvent> playgroundEvents;

    @OneToMany(mappedBy = "playground")
    private Set<PlaygroundPrediction> playgroundPredictions;

}
