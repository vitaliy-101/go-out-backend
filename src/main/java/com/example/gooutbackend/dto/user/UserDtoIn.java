package com.example.gooutbackend.dto.user;

import com.example.gooutbackend.entity.Achievement;
import com.example.gooutbackend.entity.UserSport;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDtoIn {
    private String name;
    private String priorityArea;
    private List<String> achievements;
    private List<String> sportNames;
}
