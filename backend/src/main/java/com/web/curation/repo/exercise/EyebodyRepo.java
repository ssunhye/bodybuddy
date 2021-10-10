package com.web.curation.repo.exercise;

import com.web.curation.model.exercise.Eyebody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EyebodyRepo extends JpaRepository<Eyebody, Long> {
    List<Eyebody> findByUserNoOrderByDateDesc(Long userNo);
}