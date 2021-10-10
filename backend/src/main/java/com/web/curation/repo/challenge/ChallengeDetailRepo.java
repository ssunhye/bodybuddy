package com.web.curation.repo.challenge;

import com.web.curation.model.challenge.ChallengeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeDetailRepo extends JpaRepository<ChallengeDetail, Long> {
    List<ChallengeDetail> findByChlgNoOrderByScoreDesc(Long chlgNo);
}