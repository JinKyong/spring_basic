package com.sparta.spring_basic.repository;

import com.sparta.spring_basic.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WritingRepository extends JpaRepository<Writing, Long> {
    List<Writing> findAllByOrderByIdDesc();
}