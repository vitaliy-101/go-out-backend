package com.example.gooutbackend.repository;

import com.example.gooutbackend.dto.sport.SportWithLevelDto;
import com.example.gooutbackend.entity.UserSport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSportRepository extends JpaRepository<UserSport, Long> {
    List<UserSport> findUserSportByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserSport us WHERE us.user.id = :userId AND us.sport.name = :sportName")
    void deleteByUserIdAndSportName(@Param("userId") Long userId, @Param("sportName") String sportName);

    @Modifying
    @Transactional
    @Query("UPDATE UserSport us SET us.level = :level WHERE us.user.id = :userId AND us.sport.name = :sportName")
    int updateLevelByUserIdAndSportName(@Param("userId") Long userId,
                                        @Param("sportName") String sportName,
                                        @Param("level") Integer level);
}

