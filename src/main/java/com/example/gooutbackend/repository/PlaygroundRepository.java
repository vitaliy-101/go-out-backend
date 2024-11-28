package com.example.gooutbackend.repository;

import com.example.gooutbackend.entity.Playground;
import com.example.gooutbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, Long> {

}
