package com.web.curation.repo.challenge;

import com.web.curation.model.challenge.ChallengeScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeScoreRepo extends JpaRepository<ChallengeScore, Long> {
    List<ChallengeScore> findByChlgDetailNo(Long ChlgDetailNo);
}