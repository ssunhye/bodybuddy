package com.web.curation.repo.challenge;

import com.web.curation.model.challenge.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepo extends JpaRepository<Vote, Long> {
    List<Vote> findByUserNoAndChlgNo(Long userNo, Long chlgNo);
    List<Vote> findByUserNoAndChlgDetailNo(Long userNo, Long chlgDetailNo);
    void deleteByUserNoAndChlgDetailNo(Long userNo, Long chlgDetailNo);
}