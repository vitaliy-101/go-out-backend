package com.example.gooutbackend.repository;

import com.example.gooutbackend.dto.sport.SportWithLevelDto;
import com.example.gooutbackend.entity.UserSport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSportRepository extends JpaRepository<UserSport, Long> {
    List<UserSport> findUserSportByUserId(Long userId);
}
