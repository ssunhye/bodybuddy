package com.web.curation.repo.challenge;

import com.web.curation.model.challenge.ChallengeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeLikeRepo extends JpaRepository<ChallengeLike, Long> {
    List<ChallengeLike> findByChlgNo(Long ChlgNo);
}