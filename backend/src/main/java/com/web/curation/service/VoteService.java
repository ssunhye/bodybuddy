package com.web.curation.service;

import com.web.curation.model.challenge.*;
import com.web.curation.model.exercise.Inbody;
import com.web.curation.repo.challenge.*;
import com.web.curation.repo.user.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class VoteService {
    @Autowired
    private VoteRepo voteRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChallengeDetailRepo challengeDetailRepo;

    public List<Vote> voteInfo(Long userNo, Long chlgNo) {
        return voteRepo.findByUserNoAndChlgNo(userNo, chlgNo);
    }

    public Long voteUp(VoteUpDownRequest request) {
        ChallengeDetail challengeDetail = challengeDetailRepo.findById(request.getChlgDetailNo()).get();
        challengeDetail.setScore(challengeDetail.getScore()+1);
        challengeDetailRepo.save(challengeDetail);

        Vote vote = new Vote();
        vote.setChlgNo(challengeDetailRepo.findById(request.getChlgDetailNo()).get().getChlgNo());
        vote.setUser(userRepo.findById(request.getUserNo()).get());
        vote.setChallengeDetail(challengeDetailRepo.findById(request.getChlgDetailNo()).get());
        voteRepo.save(vote);

        return challengeDetail.getScore();
    }

    @Transactional
    public Long voteDown(Long userNo, Long chlgDetailNo) {
        ChallengeDetail challengeDetail = challengeDetailRepo.findById(chlgDetailNo).get();
        challengeDetail.setScore(challengeDetail.getScore()-1);
        challengeDetailRepo.save(challengeDetail);

        voteRepo.deleteByUserNoAndChlgDetailNo(userNo, chlgDetailNo);

        return challengeDetail.getScore();
    }
}
