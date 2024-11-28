package com.example.gooutbackend.repository;

import com.example.gooutbackend.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    @Query("SELECT a.info FROM Achievement a WHERE a.user.id = :userId")
    List<String> findAchievementsByUserId(@Param("userId") Long userId);

}
