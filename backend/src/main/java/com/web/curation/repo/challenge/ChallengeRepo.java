package com.web.curation.repo.challenge;

import com.web.curation.model.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeRepo extends JpaRepository<Challenge, Long> {
    List<Challenge> findAll();

    Challenge findByChlgNo(Long ChlgNo);

    @Query(value = "select chlg from Challenge as chlg where chlg.chlgNo in (select cd.chlgNo from ChallengeDetail as cd where cd.userNo = :userNo)")
    List<Challenge> findByUserNo(@Param(value = "userNo") Long userNo);
}