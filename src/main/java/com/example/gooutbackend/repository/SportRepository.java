package com.example.gooutbackend.repository;

import com.example.gooutbackend.entity.Sport;
import com.example.gooutbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    Sport findSportByName(String name);

}
