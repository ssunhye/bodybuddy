package com.web.curation.repo.exercise;

import com.web.curation.model.exercise.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRecordRepo extends JpaRepository<TimeRecord, Long> {
    List<TimeRecord> findAllByUserNo(Long userNo);
}