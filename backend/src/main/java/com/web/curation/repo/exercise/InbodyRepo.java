package com.web.curation.repo.exercise;

import com.web.curation.model.exercise.Inbody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InbodyRepo extends JpaRepository<Inbody, Long> {
    List<Inbody> findByUserNoOrderByDateAsc(Long userNo);
    Inbody findInbodyByInbodyNoAndUserNo(Long inbodyNo, Long userNo);

}