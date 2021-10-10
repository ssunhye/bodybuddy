package com.web.curation.repo.exercise;

import com.web.curation.model.exercise.Powerlifting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerliftingRepo extends JpaRepository<Powerlifting, Long> {
    List<Powerlifting> findByUserNoOrderByDateAsc(Long userNo);
}